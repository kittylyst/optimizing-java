package handsonmjc.asynch;

/**
 *
 * @author kittylyst
 */
public class InvestorDto {
    private ApplicationStatus status;

    public InvestorDto(ApplicationStatus current) {
        status = current;
    }
    
    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
