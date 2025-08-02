package afriqueMed.business;

import afriqueMed.domain.Ticket.Intervention;
import afriqueMed.infra.operations.InterventionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class InterventionService {

    @Inject
    InterventionRepository interventionRepository;

    @Transactional
    public void createIntervention(Intervention intervention) {
        interventionRepository.save(intervention);
    }

    public Intervention getIntervention(Long id) {
        return interventionRepository.findById(id);
    }

    public List<Intervention> getAllInterventions() {
        return interventionRepository.findAll();
    }

    @Transactional
    public void deleteIntervention(Long id) {
        Intervention intervention = interventionRepository.findById(id);
        if (intervention != null) {
            interventionRepository.delete(intervention);
        }
    }
    public List<Intervention> getUndoneInterventions() {
        return interventionRepository.findUndoneInterventions();
    }

}
