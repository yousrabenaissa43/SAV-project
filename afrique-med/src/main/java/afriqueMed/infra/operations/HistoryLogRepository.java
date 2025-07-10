package afriqueMed.infra.operations;

import afriqueMed.domain.historyLog.HistoryLog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class HistoryLogRepository {

    @PersistenceContext
    EntityManager em;

    public void save(HistoryLog historyLog) {
        em.persist(historyLog);
    }

    public HistoryLog findById(Long id) {
        return em.find(HistoryLog.class, id);
    }

    public List<HistoryLog> findAll() {
        return em.createQuery("SELECT h FROM HistoryLog h", HistoryLog.class).getResultList();
    }

    public void delete(HistoryLog historyLog) {
        em.remove(historyLog);
    }

    public List<HistoryLog> findByTicketId(Long ticketId) {
        return em.createQuery(
                        "SELECT h FROM HistoryLog h WHERE h.ticket.id = :ticketId", HistoryLog.class)
                .setParameter("ticketId", ticketId)
                .getResultList();
    }

    public List<HistoryLog> findByInterventionId(Long interventionId) {
        return em.createQuery(
                        "SELECT h FROM HistoryLog h WHERE h.intervention.id = :interventionId", HistoryLog.class)
                .setParameter("interventionId", interventionId)
                .getResultList();
    }
}
