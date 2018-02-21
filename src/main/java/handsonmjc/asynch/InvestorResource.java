package handsonmjc.asynch;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kittylyst
 */
@Path(InvestorResource.RESOURCE_PATH)
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class InvestorResource {

    public static final String RESOURCE_PATH = "investors";

    private final InvestorService investorService;

    public InvestorResource(InvestorService investorService) {
        this.investorService = investorService;
    }

    @POST
    public Response createInvestor(InvestorInformationRequest investorInformationRequest,
            @Context HttpServletRequest httpServletRequest) throws Exception {
        InvestorDto investor = investorService.createInvestor(investorInformationRequest);
        // Persist DTO here...
//    log.debug("Investor created {}", investor);
        Response result = Response.ok().entity(investor).build();

        return result;
    }
}
