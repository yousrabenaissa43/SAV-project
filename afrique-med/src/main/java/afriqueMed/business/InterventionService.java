package afriqueMed.business;

import afriqueMed.domain.Ticket.Intervention;
import afriqueMed.domain.historyLog.ActionType;
import afriqueMed.domain.historyLog.HistoryLog;
import afriqueMed.infra.operations.InterventionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class InterventionService {

    @Inject
    InterventionRepository interventionRepository;
    @Inject
    HistoryLogService historyLogService;

    @Transactional
    public void createIntervention(Intervention intervention) {
        interventionRepository.save(intervention);

        // Create a history log entry
        HistoryLog log = new HistoryLog();
        log.setIntervention(intervention);
        log.setAction(ActionType.INTERVENTION_CREATED);

        String technicianName = intervention.getTechnician() != null ? intervention.getTechnician().getName() : "Unknown Technician";
        String itemName = intervention.getItem() != null ? intervention.getItem().getBasicinfo().getName() : "Unknown Item";

        log.setLogMessage(
                "Intervention (ID: " + intervention.getId() + ") for item \"" + itemName +
                        "\" was created and assigned to technician \"" + technicianName +
                        "\" on " + LocalDateTime.now()
        );

        log.setTimestamp(LocalDateTime.now());

        // Save the history log
        historyLogService.createHistoryLog(log);
    }

    public Intervention getIntervention(Long id) {
        return interventionRepository.findById(id);
    }

    public List<Intervention> getAllInterventions() {
        return interventionRepository.findAll();
    }
    //want to delete the operation not permanently (should stay in the history log)
    //still needs to be fixed
    @Transactional
    public void deleteIntervention(Long id) {
        Intervention intervention = interventionRepository.findById(id);
        if (intervention != null) {
            interventionRepository.delete(intervention);

            // Create a history log entry
            HistoryLog log = new HistoryLog();
            log.setIntervention(intervention);
            log.setAction(ActionType.INTERVENTION_DELETED);

            String technicianName = intervention.getTechnician() != null ? intervention.getTechnician().getName() : "Unknown Technician";
            String itemName = intervention.getItem() != null ? intervention.getItem().getBasicinfo().getName() : "Unknown Item";

            log.setLogMessage(
                    "Intervention (ID: " + intervention.getId() + ") for item \"" + itemName +
                            "\" assigned to technician \"" + technicianName + "\" was deleted on " + LocalDateTime.now()
            );

            log.setTimestamp(LocalDateTime.now());

            // Save the history log
            historyLogService.createHistoryLog(log);
        }
    }

    public List<Intervention> getUndoneInterventions() {
        return interventionRepository.findUndoneInterventions();
    }

}
