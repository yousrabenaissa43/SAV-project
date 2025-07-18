package afriqueMed.api.usersResources;

import afriqueMed.business.TechnicianService;
import afriqueMed.domain.Ticket.Intervention;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/technicians")
public class TechnicianResource {

    @Inject
    TechnicianService technicianService;

    @POST
    @Path("/interventions/{id}/complete")
    public Response completeIntervention(@PathParam("id") Long id, @QueryParam("notes") String notes) {
        boolean success = technicianService.completeIntervention(id, notes);
        if (success) {
            return Response.ok("Intervention marked as complete").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Intervention not found or already completed").build();
        }
    }
//tested
    @GET
    @Path("/{technicianId}/interventions/scheduled")
    public Response getScheduledInterventions(@PathParam("technicianId") Long technicianId) {
        List<Intervention> interventions = technicianService.getScheduledInterventions(technicianId);
        return Response.ok(interventions).build();
    }
}
