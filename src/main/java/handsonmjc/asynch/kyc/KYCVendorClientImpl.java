package handsonmjc.asynch.kyc;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.*;
import handsonmjc.asynch.ApplicationStatus;

/**
 *
 * @author kittylyst
 */
public class KYCVendorClientImpl implements KYCClient {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    // In the usual case, we'll time out before getting a definitive
    // response from KYC, so pass the UUIDs to an non-HTTP facing threadpool
    // for retry
    private final BlockingQueue<String> retryQueue;

    public KYCVendorClientImpl(BlockingQueue<String> downstream) {
        retryQueue = downstream;
    }

    @Override
    public KYCResponse checkPerson(PersonCheckRequest request) {
        // Fire HTTP request
        URL url;
        try {
            // FIXME
            url = new URL("http://google.com");
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Impossible state reached: ", ex);
        }
        Future<InputStream> responseF = executor.submit(() -> url.openStream());

        try {
            Thread.sleep(5000);
            if (responseF.isDone()) {
                InputStream body = responseF.get();
                // Check status of body... in this mockup assume its a PASS
                return new KYCResponse(ApplicationStatus.PASSED_KYC);
            }
        } catch (InterruptedException | ExecutionException ex) {
            // The InterruptedException shouldn't happen but an ExecutionException
            // could well indicate a real problem - so return a FAILED and
            // get a human to check on it
            return new KYCResponse(ApplicationStatus.FAILED_KYC);
        }
        try {
            retryQueue.put(request.uuid());
        } catch (InterruptedException ex) {
            // If this put() was interrupted (which should never happen) then
            // log the exception, log the missing UUID at critical level (as
            // will need to be manually fixed) and return a FAILED state
            return new KYCResponse(ApplicationStatus.FAILED_KYC);
        }
        return new KYCResponse(ApplicationStatus.PENDING_KYC);
    }

}
