package handsonmjc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author kittylyst
 */
public class ResynchingWorker implements Runnable {

    private final CyclicBarrier barrier;

    public ResynchingWorker(CyclicBarrier b) {
        barrier = b;
    }

    public void run() {
        try {
            // Wait until all Worker threads are ready
            barrier.await();
            // Simulate some work...
            Thread.sleep((int) (Math.random() * 1000));
            // Wait until all Worker threads are finished
            barrier.await();
        } catch (InterruptedException ignore) {
            // Ignore
        } catch (BrokenBarrierException bbe) {
            System.err.println("Barrier broken!");
        }
    }
}
