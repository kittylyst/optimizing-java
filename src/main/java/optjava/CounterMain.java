package optjava;

/**
 *
 * @author ben
 */
public class CounterMain {

    public static final int REPS = 10_000_000;

    public static void main(String[] args) throws InterruptedException {
        final Counter c = new Counter(); // init to 0

        Runnable r = () -> {
            for (int i = 0; i < REPS; i++) {
                c.increment();
            }
        };
        
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        int diff = c.increment() - (2 * REPS + 1);
        System.out.println("Diff: "+ diff);
    }

}
