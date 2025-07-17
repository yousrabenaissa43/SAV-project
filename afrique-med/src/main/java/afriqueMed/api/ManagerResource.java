package afriqueMed.api;


import afriqueMed.business.ManagerService;
import afriqueMed.domain.Ticket.Intervention;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/manager")
public class ManagerResource {

    @Inject
    ManagerService managerService;

    @POST
    @Path("/ticket/accept")
    public Response acceptTicket(
            @QueryParam("ticketId") Long ticketId,
            @QueryParam("technicianId") Long technicianId
    ) {
        try {
            Intervention intervention = managerService.acceptTicket(ticketId, technicianId);
            return Response.ok(intervention).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/ticket/refuse")
    public Response refuseTicket(
            @QueryParam("ticketId") Long ticketId
    ) {
        try {
            managerService.refuseTicket(ticketId);
            return Response.ok("Ticket refused successfully.").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
