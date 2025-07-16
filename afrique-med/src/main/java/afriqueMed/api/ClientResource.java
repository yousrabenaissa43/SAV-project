package afriqueMed.api;


import afriqueMed.business.ClientService;
import afriqueMed.domain.DTO.CreateTicketRequest;
import afriqueMed.domain.Ticket.Ticket;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/client")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientResource {

    @Inject
    ClientService clientService;

    @POST
    @Path("/{clientId}/ticket")
    public Response createTicketForClient(@PathParam("clientId") Long clientId, CreateTicketRequest request) {
        try {
            Ticket ticket = clientService.createTicketForClient(clientId, request);
            return Response.status(Response.Status.CREATED).entity(ticket).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to create ticket").build();
        }
    }
}
