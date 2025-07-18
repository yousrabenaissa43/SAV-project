package afriqueMed.business;

import afriqueMed.domain.Ticket.Intervention;
import afriqueMed.domain.historyLog.ActionType;
import afriqueMed.domain.historyLog.HistoryLog;
import afriqueMed.infra.operations.InterventionRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ApplicationScoped
public class TechnicianService {

    @Inject
    InterventionRepository interventionRepository;
    @Inject
    HistoryLogService historyLogService;
    /**
     * Marks an intervention as done and adds technician notes + end date.
     */
    @Transactional
    public boolean completeIntervention(Long interventionId, String notes) {
        Intervention intervention = interventionRepository.findById(interventionId);
        if (intervention == null || intervention.isDone()) {
            return false;
        }
        intervention.setDone(true);
        intervention.setEndDate(LocalDateTime.now());
        intervention.setTechnicianNotes(notes);
        interventionRepository.save(intervention);
        // Log time in message
        LocalDateTime completionTime = LocalDateTime.now();

        HistoryLog log = new HistoryLog();
        log.setIntervention(intervention);
        log.setTicket(intervention.getTicket());
        log.setUser(intervention.getTechnician()); // Assuming Technician extends User
        log.setAction(ActionType.INTERVENTION_COMPLETED);
        log.setLogMessage("Intervention #" + interventionId + " completed by technician " +
                intervention.getTechnician().getName() +
                " at " + completionTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ".");
        log.setTimestamp(completionTime);
        historyLogService.createHistoryLog(log);
        return true;
    }

    /**
     * Returns all scheduled interventions (not done) for a given technician.
     */
    public List<Intervention> getScheduledInterventions(Long technicianId) {
        return interventionRepository.findByTechnicianIdAndIsDoneFalse(technicianId);
    }
}

