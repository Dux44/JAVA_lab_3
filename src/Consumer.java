import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {
    private static AtomicInteger to_receive;
    private final Manager manager;
    private final int id;

    public static void set_max_receive(int to_receive) {
        Consumer.to_receive = new AtomicInteger(to_receive);
    }

    public Consumer(Manager manager, int id) {
        this.manager = manager;
        this.id = id;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (to_receive.getAndDecrement() > 0) {
            try {
                manager.empty_sem.acquire();
                manager.occupied_sem.acquire();

                Item item = manager.items.remove(0);
                System.out.printf("\u001B[36m" + "Receiver #%d takes item %d\n" + "\u001B[0m", this.id, item.getId());

                manager.occupied_sem.release();
                manager.full_sem.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("\u001B[31m" + "Receiver #" + this.id + " finished working" + "\u001B[0m");
    }
}
