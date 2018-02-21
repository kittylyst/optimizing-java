package handsonmjc.asynch.kyc;

import java.util.concurrent.*;

/**
 *
 * @author kittylyst
 */

// Will be spun up from Spring
public class KYCRetryManager implements Runnable {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    // Injected
    private final BlockingQueue<String> incoming;

    // Injected
    private final BlockingQueue<String> downstream;

    private volatile boolean shutdown = false;

    public KYCRetryManager(BlockingQueue<String> toProcess, BlockingQueue<String> forEthereum) {
        incoming = toProcess;
        downstream = forEthereum;
    }

    public void shutdown() {
        shutdown = true;
    }

    @Override
    public void run() {
        while (!shutdown) {
            try {
                Thread.sleep(500);
                        
                String next = incoming.take();
                if (next != null) {
                    executor.submit(new KYCRetryTask(next));
                }
            } catch (InterruptedException ex) {
                // IGNORE
            }
        }
    }

    final class KYCRetryTask implements Runnable {

        private final String id;

        private KYCRetryTask(String next) {
            id = next;
        }

        @Override
        public void run() {
            // KYC retry logic goes here...

            try {
                // Simulate a possible success case...
                if (Math.random() > 0.5) {
                    // If passed...
                    //
                    // Call update service to update status to PASSED for request id
                    // ...
                    // Then send to Ethereum
                    downstream.put(id);
                    return;
                }

                // Otherwise, still pending.. so retry the KYC lookup
                // by putting the ID back on the queue for retry
                incoming.put(id);
            } catch (InterruptedException ex) {
                // If this put() was interrupted (which should never happen) then
                // log the exception, log the missing UUID at critical level (as
                // will need to be manually fixed) and return a FAILED state

            }
        }
    }
}
