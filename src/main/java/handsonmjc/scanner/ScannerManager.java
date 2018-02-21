package handsonmjc.scanner;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ben
 */
public class ScannerManager implements Runnable {
    
    private final BlockingQueue<MethodScanner> forProcessing;
    private final ExecutorService pool = Executors.newSingleThreadExecutor();

    private volatile boolean shutdown = false;
    
    public ScannerManager(BlockingQueue<MethodScanner> q) {
        forProcessing = q;
    }
    
    public void run() {
        while (!shutdown) {
            try {
                MethodScanner ms = forProcessing.poll(1, TimeUnit.SECONDS);
                if (ms != null) {
                    ms.setQueue(forProcessing);
                    pool.execute(ms);
                } else {
                    if (forProcessing.size() == 0) {
                        shutdown = true;
                    }
                }
            } catch (InterruptedException ex) {
                shutdown = true;
            }
        }
        pool.shutdown();
    }
    
}
