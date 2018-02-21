package handsonmjc.asynch.ethereum;

import java.util.concurrent.CompletableFuture;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 *
 * @author kittylyst
 */
public interface WhitelistClient {

    public CompletableFuture<TransactionReceipt> whitelist(String uuid);

}
