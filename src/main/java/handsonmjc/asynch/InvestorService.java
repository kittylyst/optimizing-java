package handsonmjc.asynch;

import java.util.concurrent.BlockingQueue;
import handsonmjc.asynch.kyc.KYCClient;
import handsonmjc.asynch.kyc.KYCResponse;
import handsonmjc.asynch.kyc.PersonCheckRequest;

/**
 *
 * @author kittylyst
 */
public class InvestorService {

    // Injected
    private KYCClient kyc;

    // Injected
    private BlockingQueue<String> toEthereum;

    public InvestorDto createInvestor(InvestorInformationRequest investorInformationRequest) {
        // First send to KYC
        final PersonCheckRequest pcr = new PersonCheckRequest(investorInformationRequest);
        final KYCResponse response = kyc.checkPerson(pcr);
        final ApplicationStatus status = response.getStatus();

        switch (status) {
            case FAILED_KYC:
                return new InvestorDto(ApplicationStatus.FAILED_KYC);
            case PASSED_KYC: {
                try {
                    // Passed immediately - so fwd to Ethereum
                    toEthereum.put(pcr.uuid());
                } catch (InterruptedException ex) {
                    // Failed, so log at high level for manual retry
                    return new InvestorDto(ApplicationStatus.FAILED_KYC);
                }
            }
            return new InvestorDto(ApplicationStatus.PENDING_KYC);
            case PENDING_KYC:
                return new InvestorDto(ApplicationStatus.PENDING_KYC);
            default:
                // Can't happen
                throw new IllegalStateException("Impossible response code: " + status + " seen from KYC");
        }
    }

    InvestorDto lookup(long applicationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
