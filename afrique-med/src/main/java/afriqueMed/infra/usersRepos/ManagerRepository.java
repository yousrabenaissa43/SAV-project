package afriqueMed.infra.usersRepos;

import afriqueMed.domain.users.Manager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class ManagerRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Manager manager) {
        if (manager.getId() == null) {
            em.persist(manager);
        } else {
            em.merge(manager);
        }
    }

    public Manager findById(Long id) {
        return em.find(Manager.class, id);
    }

    public List<Manager> findAll() {
        return em.createQuery("SELECT m FROM Manager m", Manager.class).getResultList();
    }

    public void delete(Manager manager) {
        em.remove(em.contains(manager) ? manager : em.merge(manager));
    }
}
