import java.util.ArrayList;
import java.util.concurrent.Semaphore;
public class Manager {
    public final Semaphore empty_sem;
    public final Semaphore full_sem;
    public final Semaphore occupied_sem;

    public ArrayList<Item> items;

    public Manager(int storageSize) {
        occupied_sem = new Semaphore(1);
        full_sem = new Semaphore(storageSize);
        empty_sem = new Semaphore(0);
        this.items = new ArrayList<Item>(storageSize);
    }

}
