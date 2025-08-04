package afriqueMed.infra.equipmentrepos;

import afriqueMed.domain.Purchase;
import afriqueMed.domain.equipement.Item;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class PurchaseRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Purchase purchase) {
        if (purchase.getId() == null) {
            em.persist(purchase);
        } else {
            em.merge(purchase);
        }
    }

    public Purchase findById(Long id) {
        return em.find(Purchase.class, id);
    }

    public List<Purchase> findAll() {
        return em.createQuery("SELECT p FROM Purchase p", Purchase.class).getResultList();
    }

    public void delete(Purchase purchase) {
        em.remove(em.contains(purchase) ? purchase : em.merge(purchase));
    }

    public List<Purchase> findByClientId(Long clientId) {
        return em.createQuery("SELECT p FROM Purchase p WHERE p.client.id = :clientId", Purchase.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }
    public List<Item> findItemsByClientId(Long clientId) {
        return em.createQuery(
                        "SELECT p.item FROM Purchase p WHERE p.client.id = :clientId", Item.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }
}
