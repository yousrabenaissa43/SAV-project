package afriqueMed.infra.operations;

import afriqueMed.domain.Ticket.Intervention;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class InterventionRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Intervention intervention) {
        em.persist(intervention);
    }

    public Intervention findById(Long id) {
        return em.find(Intervention.class, id);
    }

    public List<Intervention> findAll() {
        return em.createQuery("SELECT i FROM Intervention i", Intervention.class).getResultList();
    }

    public void delete(Intervention intervention) {
        em.remove(intervention);
    }

    public List<Intervention> findByTechnicianId(Long technicianId) {
        return em.createQuery(
                        "SELECT i FROM Intervention i WHERE i.technician.id = :technicianId", Intervention.class)
                .setParameter("technicianId", technicianId)
                .getResultList();
    }

    public Intervention findByTicketId(Long ticketId) {
        return em.createQuery(
                        "SELECT i FROM Intervention i WHERE i.ticket.id = :ticketId", Intervention.class)
                .setParameter("ticketId", ticketId)
                .getSingleResult();
    }
    public List<Intervention> findByTechnicianIdAndIsDoneFalse(Long technicianId) {
        return em.createQuery(
                        "SELECT i FROM Intervention i WHERE i.technician.id = :technicianId AND i.isDone = false",
                        Intervention.class)
                .setParameter("technicianId", technicianId)
                .getResultList();
    }
    public List<Intervention> findByStartDateBetween(LocalDateTime start, LocalDateTime end) {
        return em.createQuery(
                        "SELECT i FROM Intervention i WHERE i.startDate >= :start AND i.startDate < :end", Intervention.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }
    public List<Intervention> findUndoneInterventions() {
        return em.createQuery("SELECT i FROM Intervention i WHERE i.isDone = false", Intervention.class)
                .getResultList();
    }





}
