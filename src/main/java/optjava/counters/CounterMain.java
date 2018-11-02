package optjava.counters;

/**
 * @author ben
 */
public class CounterMain {

    public static final int REPS = 10_000_000;

    public static void main(String[] args) throws InterruptedException {
        final Counter c = new UnprotectedCounter(); // init to 0

        Runnable r = () -> {
            for (int i = 0; i < REPS; i++) {
                c.increment();
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long fin = System.currentTimeMillis();
        int diff = c.increment() - (2 * REPS + 1);
        System.out.println("Diff: " + diff);
        System.out.println("Elapsed: " + (fin - start));
    }

}
