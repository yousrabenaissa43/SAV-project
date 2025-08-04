package afriqueMed.api.usersResources;


import afriqueMed.business.ClientService;
import afriqueMed.business.PurchaseService;
import afriqueMed.domain.DTO.CreateTicketRequest;
import afriqueMed.domain.Ticket.Ticket;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/api/client")
public class ClientResource {

    @Inject
    ClientService clientService;
    @Inject
    PurchaseService purchaseService;

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
    @GET
    @Path("/{id}/purchases")
    public Response getClientPurchases(@PathParam("id") Long clientId) {
        var items = purchaseService.getItemsPurchasedByClient(clientId);
        return Response.ok(items).build();
    }
}
