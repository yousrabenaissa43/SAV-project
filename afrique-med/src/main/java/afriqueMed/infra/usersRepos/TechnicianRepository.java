package afriqueMed.infra.usersRepos;

import afriqueMed.domain.CountryEnum;
import afriqueMed.domain.users.Technician;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class TechnicianRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Technician technician) {
        if (technician.getId() == null) {
            em.persist(technician);
        } else {
            em.merge(technician);
        }
    }

    public Technician findById(Long id) {
        return em.find(Technician.class, id);
    }

    public List<Technician> findAll() {
        return em.createQuery("SELECT t FROM Technician t", Technician.class).getResultList();
    }

    public void delete(Technician technician) {
        em.remove(em.contains(technician) ? technician : em.merge(technician));
    }

    public List<Technician> findByVacationStatus(boolean isOnVacation) {
        return em.createQuery("SELECT t FROM Technician t WHERE t.isOnVacation = :vacation", Technician.class)
                .setParameter("vacation", isOnVacation)
                .getResultList();
    }

    public List<Technician> findByLocation(CountryEnum location) {
        return em.createQuery("SELECT t FROM Technician t WHERE t.location = :location", Technician.class)
                .setParameter("location", location)
                .getResultList();
    }
    public List<Technician> findAvailableTechnicians() {
        return em.createQuery("SELECT t FROM Technician t WHERE t.isOnVacation = false", Technician.class)
                .getResultList();
    }

}
