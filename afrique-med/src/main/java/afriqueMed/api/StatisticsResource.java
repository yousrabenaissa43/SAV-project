package afriqueMed.api;

import afriqueMed.business.StatisticsService;
import afriqueMed.domain.Ticket.Status;
import afriqueMed.domain.Ticket.TicketType;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

@Path("/statistics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatisticsResource {

    @Inject
    StatisticsService statisticsService;

    @GET
    @Path("/total-tickets")
    public Response getTotalTickets() {
        return Response.ok(statisticsService.getTotalTickets()).build();
    }

    @GET
    @Path("/tickets-by-status")
    public Response getTicketsByStatus() {
        Map<Status, Long> data = statisticsService.getTicketCountPerStatus();
        return Response.ok(data).build();
    }

    @GET
    @Path("/interventions-by-technician")
    public Response getInterventionsByTechnician() {
        return Response.ok(statisticsService.getInterventionsPerTechnician()).build();
    }

    @GET
    @Path("/tickets-by-type")
    public Response getTicketsByType() {
        Map<TicketType, Long> data = statisticsService.getTicketCountPerType();
        return Response.ok(data).build();
    }

    @GET
    @Path("/interventions-by-day")
    public Response getInterventionsPerDay(@QueryParam("start") String start,
                                           @QueryParam("end") String end) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.parse(start, formatter);
            LocalDate endDate = LocalDate.parse(end, formatter);
            return Response.ok(statisticsService.getInterventionsPerDay(startDate, endDate)).build();
        } catch (DateTimeParseException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid date format. Use dd/MM/yyyy, like 01/07/2025").build();
        }
    }


    @GET
    @Path("/decommissioned-items")
    public Response countDecommissionedItems() {
        return Response.ok(statisticsService.countDecommissionedItems()).build();
    }

    @GET
    @Path("/actions-by-type")
    public Response getActionCountsByType() {
        return Response.ok(statisticsService.getActionCountsByType()).build();
    }

    @GET
    @Path("/top-clients")
    public Response getTopClients(@QueryParam("limit") @DefaultValue("5") int limit) {
        return Response.ok(statisticsService.getTopClientsByTicketCount(limit)).build();
    }

    @GET
    @Path("/average-resolution-time")
    public Response getAverageResolutionTime() {
        return Response.ok(statisticsService.getAverageResolutionTimeHours()).build();
    }

    @GET
    @Path("/monthly-ticket-trend")
    public Response getMonthlyTicketTrends(@QueryParam("months") @DefaultValue("6") int months) {
        return Response.ok(statisticsService.getMonthlyTicketTrends(months)).build();
    }
    @GET
    @Path("/avg-interventions-by-day")
    public Response getAverageInterventionsPerDay(@QueryParam("start") String start,
                                                  @QueryParam("end") String end) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.parse(start, formatter);
            LocalDate endDate = LocalDate.parse(end, formatter);
            double average = statisticsService.getAverageInterventionsPerDay(startDate, endDate);
            return Response.ok(average).build();
        } catch (DateTimeParseException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid date format. Use dd/MM/yyyy, like 01/07/2025").build();
        }
    }

}
