package afriqueMed.api;

import afriqueMed.business.HistoryLogService;
import afriqueMed.domain.historyLog.HistoryLog;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/history-logs")
public class HistoryLogResource {

    @Inject
    HistoryLogService historyLogService;

    @POST
    public Response createHistoryLog(HistoryLog historyLog) {
        historyLogService.createHistoryLog(historyLog);
        return Response.status(Response.Status.CREATED).entity(historyLog).build();
    }

    @GET
    @Path("/{id}")
    public Response getHistoryLog(@PathParam("id") Long id) {
        HistoryLog historyLog = historyLogService.getHistoryLog(id);
        if (historyLog != null) {
            return Response.ok(historyLog).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<HistoryLog> getAllHistoryLogs() {
        return historyLogService.getAllHistoryLogs();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteHistoryLog(@PathParam("id") Long id) {
        historyLogService.deleteHistoryLog(id);
        return Response.noContent().build();
    }
}
