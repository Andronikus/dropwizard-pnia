package pt.andronikus.pnia.resources;

import pt.andronikus.pnia.api.AggregationInfo;
import pt.andronikus.pnia.api.PhoneList;
import pt.andronikus.pnia.controller.AggregatorController;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class PhoneNumberAggregatorResource {

    private final AggregatorController aggregatorController;

    public PhoneNumberAggregatorResource() {
        this.aggregatorController = new AggregatorController();
    }

    @POST
    @Path("/aggregate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response aggregatePhoneInformation(@NotNull PhoneList phoneList){
            AggregationInfo aggregationInfo = aggregatorController.aggregatePhoneInfo(phoneList);
            return Response.status(Response.Status.OK).entity(aggregationInfo).build();
    }
}
