package optjava;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * @author ben
 */
// tag::MODEL_ALLOC[]
public class ModelAllocator implements Runnable {
    private volatile boolean shutdown = false;

    private double chanceOfLongLived = 0.02;
    private int multiplierForLongLived = 20;
    private int x = 1024;
    private int y = 1024;
    private int mbPerSec = 50;
    private int shortLivedMs = 100;
    private int nThreads = 8;
    private Executor exec = Executors.newFixedThreadPool(nThreads);    
// end::MODEL_ALLOC[]

    public static void main(String[] args) {
        ModelAllocator ma = new ModelAllocator();
        ma.run();
    }

// tag::MODEL_ALLOC_MAIN[]
    public void run() {
        final int mainSleep = (int) (1000.0 / mbPerSec);

        while (!shutdown) {
            for (int i = 0; i < mbPerSec; i++) {
                ModelObjectAllocation to = new ModelObjectAllocation(x, y, lifetime());
                exec.execute(to);
                try {
                    Thread.sleep(mainSleep);
                } catch (InterruptedException ex) {
                    shutdown = true;
                }
            }
        }
    }

    // Simple function to model Weak Generational Hypothesis
    // Returns the expected lifetime of an object - usually this
    // is very short, but there is a small chance of an object
    // being "long-lived"
    public int lifetime() {
        if (Math.random() < chanceOfLongLived) {
            return multiplierForLongLived * shortLivedMs;
        }

        return shortLivedMs;
    }
}
// end::MODEL_ALLOC_MAIN[]
