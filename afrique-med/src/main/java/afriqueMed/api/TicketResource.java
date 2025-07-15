package afriqueMed.api;

import afriqueMed.business.TicketService;
import afriqueMed.domain.Ticket.Ticket;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/tickets")
public class TicketResource {

    @Inject
    TicketService ticketService;

    @POST
    public Response createTicket(Ticket ticket) {
        ticketService.createTicket(ticket);
        return Response.status(Response.Status.CREATED).entity(ticket).build();
    }

    @GET
    @Path("/{id}")
    public Response getTicket(@PathParam("id") Long id) {
        Ticket ticket = ticketService.getTicket(id);
        if (ticket != null) {
            return Response.ok(ticket).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTicket(@PathParam("id") Long id) {
        ticketService.deleteTicket(id);
        return Response.noContent().build();
    }
}
