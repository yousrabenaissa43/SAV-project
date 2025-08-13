package afriqueMed.api.usersResources;

import afriqueMed.business.TechnicianService;
import afriqueMed.domain.Ticket.Intervention;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/technicians")
public class TechnicianResource {

    @Inject
    TechnicianService technicianService;

    @POST
    @Path("/interventions/{id}/complete")
    public Response completeIntervention(@PathParam("id") Long id) {
        boolean success = technicianService.completeIntervention(id);
        if (success) {
            return Response.ok("Intervention marked as complete").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Intervention not found or already completed").build();
        }
    }
    @PUT
    @Path("/interventions/{id}/cancel")
    public Response cancelIntervention(@PathParam("id") Long id) {
        boolean success = technicianService.cancelIntervention(id);
        if (!success) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Intervention not found or already cancelled.")
                    .build();
        }
        return Response.ok("Intervention cancelled successfully.").build();
    }


    //tested
    @GET
    @Path("/{technicianId}/interventions/scheduled")
    public Response getScheduledInterventions(@PathParam("technicianId") Long technicianId) {
        List<Intervention> interventions = technicianService.getScheduledInterventions(technicianId);
        return Response.ok(interventions).build();
    }
    //tested
    @PATCH
    @Path("/{id}/decommission")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response markItemToBeDecommissioned(@PathParam("id") Long id, String reason) {
        boolean success = technicianService.markItemToBeDecommissioned(id, reason);
        if (success) {
            return Response.ok("Item marked for decommissioning.").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Failed to mark item as decommissioned.").build();
        }
    }
    @PUT
    @Path("/interventions/{id}/notes")
    @Consumes("text/plain")
    public Response addNotes(@PathParam("id") Long id, String notes) {
        technicianService.addNotes(id, notes);
        return Response.ok().build();
    }



}
