
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int num_consumers = 2;
        int num_producers = 3;
        int vault_size = 3;
        int total_items = 10;

        Manager manager = new Manager(vault_size);
        Consumer.set_max_receive(total_items);
        Producer.set_max_supply(total_items);

        for (int i = 0; i < num_producers; i++) {
            new Producer(manager, i + 1);
        }

        for (int i = 0; i < num_consumers; i++) {
            new Consumer(manager, i + 1);
        }

    }
}
