import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private static AtomicInteger to_supply;
    private final Manager manager;
    private final int id;

    public static void set_max_supply(int to_supply) {
        Producer.to_supply = new AtomicInteger(to_supply);
    }

    public Producer(Manager manager, int id) {
        this.manager = manager;
        this.id = id;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (to_supply.getAndDecrement() > 0) {
            try {
                manager.full_sem.acquire();
                manager.occupied_sem.acquire();

                Random random = new Random();
                Item item = new Item(random.nextInt(0, 100));
                manager.items.add(item);
                System.out.printf("\u001B[33m" + "Producer #%d adds new item %d\n" + "\u001B[0m", this.id, item.getId());

                manager.occupied_sem.release();
                manager.empty_sem.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("\u001B[35m" + "Producer #" + this.id + " finished working" + "\u001B[0m");
    }
}