package afriqueMed.infra.usersRepos;

import afriqueMed.domain.users.Client;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class ClientRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Client client) {
        if (client.getId() == null) {
            em.persist(client);
        } else {
            em.merge(client);
        }
    }

    public Client findById(Long id) {
        return em.find(Client.class, id);
    }

    public List<Client> findAll() {
        return em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
    }

    public void delete(Client client) {
        em.remove(em.contains(client) ? client : em.merge(client));
    }
}
