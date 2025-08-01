package afriqueMed.business;

import afriqueMed.domain.Ticket.Intervention;
import afriqueMed.domain.Ticket.Status;
import afriqueMed.domain.Ticket.Ticket;
import afriqueMed.domain.historyLog.ActionType;
import afriqueMed.domain.historyLog.HistoryLog;
import afriqueMed.domain.users.Technician;
import afriqueMed.infra.operations.InterventionRepository;
import afriqueMed.infra.usersRepos.TechnicianRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class ManagerService {

    @Inject
    TicketService ticketService;

    @Inject
    InterventionRepository interventionRepository;

    @Inject
    TechnicianRepository technicianRepository;

    @Inject
    HistoryLogService historyLogService;

    //not tested, need to create a technician and a method to promote a user to technician
    @Transactional
    public Intervention acceptTicket(Long ticketId, Long technicianId) {
        Ticket ticket = ticketService.getTicket(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found with ID: " + ticketId);
        }
        ticket.setStatus(Status.ACCEPTED);
        Technician technician = technicianRepository.findById(technicianId);

        if (technician == null) {
            throw new IllegalArgumentException("Technician not found with ID: " + technicianId);
        }

        Intervention intervention = new Intervention();
        intervention.setItem(ticket.getItem());
        intervention.setTechnician(technician);
        intervention.setStartDate(LocalDateTime.now());
        intervention.setDone(false);
        intervention.setAddress(ticket.getLocation());
        interventionRepository.save(intervention);

        // 3. Log ticket acceptance
        HistoryLog ticketAcceptedLog = new HistoryLog();
        ticketAcceptedLog.setTicket(ticket);
        ticketAcceptedLog.setUser(ticket.getUser()); // Or the manager if available
        ticketAcceptedLog.setAction(ActionType.TICKET_ACCEPTED);
        ticketAcceptedLog.setLogMessage("Ticket ID " + ticketId + " has been accepted by the manager.");
        ticketAcceptedLog.setTimestamp(LocalDateTime.now());

        historyLogService.createHistoryLog(ticketAcceptedLog);
        // Log the creation
        HistoryLog log = new HistoryLog();
        log.setTicket(ticket);
        log.setUser(ticket.getUser());
        log.setAction(ActionType.INTERVENTION_CREATED);
        log.setLogMessage("Intervention created for ticket ID " + ticketId +
                " and assigned to technician " + technician.getName());
        log.setTimestamp(LocalDateTime.now());

        historyLogService.createHistoryLog(log);

        return intervention;
    }
    //tested
    @Transactional
    public void refuseTicket(Long ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found with ID: " + ticketId);
        }
        // Update ticket status
        ticket.setStatus(Status.REFUSED);

        // Log the refusal
        HistoryLog refusalLog = new HistoryLog();
        refusalLog.setTicket(ticket);
        refusalLog.setAction(ActionType.TICKET_REFUSED);
        refusalLog.setLogMessage("Ticket ID " + ticketId + " was refused by manager on " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        refusalLog.setTimestamp(LocalDateTime.now());

        historyLogService.createHistoryLog(refusalLog);
    }
}
