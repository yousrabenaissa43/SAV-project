package afriqueMed.business;

import afriqueMed.domain.Ticket.Intervention;
import afriqueMed.domain.equipement.Item;
import afriqueMed.domain.equipement.ItemStatus;
import afriqueMed.domain.historyLog.ActionType;
import afriqueMed.domain.historyLog.HistoryLog;
import afriqueMed.infra.operations.InterventionRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ApplicationScoped
public class TechnicianService {

    @Inject
    InterventionRepository interventionRepository;
    @Inject
    HistoryLogService historyLogService;
    @Inject
    InterventionService interventionService;
    /**
     * Marks an intervention as done and adds technician notes + end date.
     */
    @Transactional
    public boolean completeIntervention(Long interventionId) {
        Intervention intervention = interventionRepository.findById(interventionId);
        if (intervention == null || intervention.isDone()) {
            return false;
        }
        intervention.setDone(true);
        intervention.setEndDate(LocalDateTime.now());
        interventionRepository.save(intervention);
        // Log time in message
        LocalDateTime completionTime = LocalDateTime.now();

        HistoryLog log = new HistoryLog();
        log.setIntervention(intervention);
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
    @Transactional
    public boolean markItemToBeDecommissioned(Long interventionId, String reason) {
        Intervention intervention = interventionRepository.findById(interventionId);
//        if (intervention == null || intervention.isDone()) {
//            return false;
//        }

        Item item = intervention.getItem();
        item.setItemStatus(ItemStatus.DECOMMISSIONED);
        intervention.setSetItemToBeDecommissioned(true);
        String existingNotes = intervention.getTechnicianNotes() != null ? intervention.getTechnicianNotes() + "\n" : "";
        String newNote = "REASON TO BE DECOMMISSIONED: " + reason;
        intervention.setTechnicianNotes(existingNotes + newNote);

        interventionRepository.save(intervention);

        // Log the action
        LocalDateTime now = LocalDateTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        HistoryLog log = new HistoryLog();
        log.setIntervention(intervention);
        log.setUser(intervention.getTechnician());
        log.setAction(ActionType.INTERVENTION_UPDATED);
        log.setLogMessage(
                String.format(
                        "Technician '%s' marked the item '%s' (ID: %d) as DECOMMISSIONED during intervention ID: %d on %s. Reason provided: \"%s\".",
                        intervention.getTechnician().getName(),
                        item.getBasicinfo().getName(),
                        item.getId(),
                        intervention.getId(),
                        formattedTime,
                        reason
                )
        );
        log.setTimestamp(now);
        historyLogService.createHistoryLog(log);

        return true;
    }
    @Transactional
    public void addNotes(Long interventionId, String newNotes) {
        Intervention intervention = interventionService.getIntervention(interventionId);
        if (intervention == null) {
            throw new WebApplicationException("Intervention not found", 404);
        }

        String existingNotes = intervention.getTechnicianNotes();
        if (existingNotes == null || existingNotes.isBlank()) {
            intervention.setTechnicianNotes(intervention.getTechnician().getName()+ ":"+ newNotes);
        } else {
            intervention.setTechnicianNotes(existingNotes + "\n"+ intervention.getTechnician().getName()+ ":"+ newNotes);
        }

        interventionService.save(intervention);
    }


}

