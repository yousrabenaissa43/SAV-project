package afriqueMed.api;

import afriqueMed.business.InterventionService;
import afriqueMed.domain.Ticket.Intervention;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/interventions")
public class InterventionResource {

    @Inject
    InterventionService interventionService;

    @POST
    public Response createIntervention(Intervention intervention) {
        interventionService.createIntervention(intervention);
        return Response.status(Response.Status.CREATED).entity(intervention).build();
    }

    @GET
    @Path("/{id}")
    public Response getIntervention(@PathParam("id") Long id) {
        Intervention intervention = interventionService.getIntervention(id);
        if (intervention != null) {
            return Response.ok(intervention).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<Intervention> getAllInterventions() {
        return interventionService.getAllInterventions();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteIntervention(@PathParam("id") Long id) {
        interventionService.deleteIntervention(id);
        return Response.noContent().build();
    }
    @GET
    @Path("/undone")
    public Response getUndoneInterventions() {
        List<Intervention> undone = interventionService.getUndoneInterventions();
        return Response.ok(undone).build();
    }
    @PUT
    @Path("/{id}/technician")
    @Consumes("text/plain")
    public Response updateTechnician(@PathParam("id") Long id, String technicianIdString) {
        try {
            long technicianId = Long.parseLong(technicianIdString.trim());
            interventionService.updateTechnician(id, technicianId);
            return Response.ok().build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid technician ID").build();
        }
    }




}
