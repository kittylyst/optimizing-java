package handsonmjc;


import java.util.concurrent.CyclicBarrier;


/**
 *
 * @author kittylyst
 */
public class ResynchingDriver {

    public static void main(String[] args) throws Exception {
        int nThreads = Integer.parseInt(args[0]);
        CyclicBarrier barrier = new CyclicBarrier(nThreads, () -> System.out.println("Barrier reached"));
        for (int i = 0; i < nThreads; i++) {
            new Thread(new ResynchingWorker(barrier)).start();
        }
        Thread.sleep(5000);
    }

}
