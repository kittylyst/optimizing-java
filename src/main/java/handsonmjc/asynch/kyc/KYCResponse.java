package handsonmjc.asynch.kyc;

import handsonmjc.asynch.ApplicationStatus;

/**
 *
 * @author kittylyst
 */
public class KYCResponse {
    private final ApplicationStatus status;

    public KYCResponse(ApplicationStatus result) {
        status = result;
    }
    
    public ApplicationStatus getStatus() {
        return status;
    }

}
