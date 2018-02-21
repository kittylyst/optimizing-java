package handsonmjc.asynch.ethereum;

import java.util.concurrent.CompletableFuture;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 *
 * @author ben
 */
public class WhitelistClientImpl implements WhitelistClient {

    @Override
    public CompletableFuture<TransactionReceipt> whitelist(String uuid) {
        Whitelist wl = null; // = Whitelist.load(uuid, null, null, BigInteger.ZERO, BigInteger.ZERO);
        return wl.addWallet(uuid, uuid).sendAsync();
    }

}
