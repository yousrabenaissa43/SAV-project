package afriqueMed.infra.usersRepos;

import afriqueMed.domain.users.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public void save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    // Exemple méthode pour chercher par keycloakId
    public User findByKeycloakId(String keycloakId) {
        List<User> users = em.createQuery("SELECT u FROM User u WHERE u.keycloakId = :keycloakId", User.class)
                .setParameter("keycloakId", keycloakId)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

}
