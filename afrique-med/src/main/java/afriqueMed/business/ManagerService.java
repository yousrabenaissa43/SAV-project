package afriqueMed.business;

import afriqueMed.domain.Ticket.Intervention;
import afriqueMed.domain.Ticket.Status;
import afriqueMed.domain.Ticket.Ticket;
import afriqueMed.domain.Ticket.TicketType;
import afriqueMed.domain.equipement.ItemStatus;
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
        if (ticket.getTicketType() == TicketType.MAINTENANCE
                || ticket.getTicketType() == TicketType.REPAIR) {
            ticket.getItem().setItemStatus(ItemStatus.UNDER_REPAIR);
        }

        intervention.setItem(ticket.getItem());
        intervention.setTechnician(technician);
        intervention.setStartDate(LocalDateTime.now());
        intervention.setStatus(Status.IN_PROGRESS);
        intervention.setAddress(ticket.getLocation());
        intervention.setTicketType(ticket.getTicketType());
        interventionRepository.save(intervention);
        // --- Create History Log ---
        HistoryLog log = new HistoryLog();
        log.setTicket(ticket);
        log.setIntervention(intervention);
        log.setUser(technician);
        log.setAction(ActionType.INTERVENTION_STARTED);
        log.setLogMessage("Technician " + technician.getName() +
                " has started working on intervention " + intervention.getId() +
                " of type " + intervention.getTicketType() +
                " on " + LocalDateTime.now());
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

    }
}
