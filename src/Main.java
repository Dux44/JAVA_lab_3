
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int num_receivers = 2;
        int num_suppliers = 3;
        int vault_size = 3;
        int total_items = 10;

        Manager mananger = new Manager(vault_size);
        Consumer.set_max_receive(total_items);
        Producer.set_max_supply(total_items);

        for (int i = 0; i < num_suppliers; i++) {
            new Producer(mananger, i + 1);
        }

        for (int i = 0; i < num_receivers; i++) {
            new Consumer(mananger, i + 1);
        }

    }
}
