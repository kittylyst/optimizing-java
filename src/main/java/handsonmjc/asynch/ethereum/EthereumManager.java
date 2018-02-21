package handsonmjc.asynch.ethereum;

import java.util.concurrent.*;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 *
 * @author kittylyst
 */
// Will be spun up from Spring
public class EthereumManager implements Runnable {

    private final ScheduledExecutorService stp = Executors.newScheduledThreadPool(4);

    // Injected
    private final BlockingQueue<String> incoming;

    // Injected
    private WhitelistClient ether;

    private volatile boolean shutdown = false;

    public EthereumManager(BlockingQueue<String> toProcess) {
        incoming = toProcess;
    }

    public void shutdown() {
        shutdown = true;
    }

    @Override
    public void run() {
        while (!shutdown) {
            try {
                String next = incoming.take();
                if (next != null) {
                    final Future<TransactionReceipt> cf = ether.whitelist(next);
                    // TXN submitted to Ethereum, now fire off a completion task
                    final EthereumRetryTask task = new EthereumRetryTask(cf, 1);
                    stp.schedule(task, 1, TimeUnit.SECONDS);
                }
            } catch (InterruptedException ex) {
                // IGNORE
            }
        }
    }

    final class EthereumRetryTask implements Runnable {

        private final Future<TransactionReceipt> ethTask;
        private final int delay;
        
        private EthereumRetryTask(Future<TransactionReceipt> cf, int secs) {
            ethTask = cf;
            delay = secs;
        }

        @Override
        public void run() {
            try {
                TransactionReceipt tr = ethTask.get(1, TimeUnit.SECONDS);
                // Update record with results of Ethereum transaction
                // Hopefully (probably) PASSED
                
            } catch (InterruptedException | ExecutionException ex) {
                // Update record with a FAILED status

            } catch (TimeoutException ex) {
                // Resubmit with back-off
                final int newDelay = 2 * delay < 100 ? 2 * delay : 64;
                stp.schedule(new EthereumRetryTask(ethTask, newDelay), newDelay, TimeUnit.SECONDS);
            }
        }
        
    }

    
}
