package handsonmjc.asynch.kyc;

/**
 *
 * @author kittylyst
 */
public interface KYCClient {

    public KYCResponse checkPerson(PersonCheckRequest request);
}
