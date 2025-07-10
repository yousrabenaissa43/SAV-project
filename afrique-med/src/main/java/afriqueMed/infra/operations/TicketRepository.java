package afriqueMed.infra.operations;

import afriqueMed.domain.Ticket.Ticket;
import afriqueMed.domain.Ticket.Status;
import afriqueMed.domain.Ticket.Priority;
import afriqueMed.domain.CountryEnum;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class TicketRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Ticket ticket) {
        em.persist(ticket);
    }

    public Ticket findById(Long id) {
        return em.find(Ticket.class, id);
    }

    public List<Ticket> findAll() {
        return em.createQuery("SELECT t FROM Ticket t", Ticket.class).getResultList();
    }

    public void delete(Ticket ticket) {
        em.remove(ticket);
    }

    public List<Ticket> findByStatus(Status status) {
        return em.createQuery(
                        "SELECT t FROM Ticket t WHERE t.status = :status", Ticket.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<Ticket> findByPriority(Priority priority) {
        return em.createQuery(
                        "SELECT t FROM Ticket t WHERE t.priority = :priority", Ticket.class)
                .setParameter("priority", priority)
                .getResultList();
    }

    public List<Ticket> findByCountry(CountryEnum country) {
        return em.createQuery(
                        "SELECT t FROM Ticket t WHERE t.country = :country", Ticket.class)
                .setParameter("country", country)
                .getResultList();
    }
}
