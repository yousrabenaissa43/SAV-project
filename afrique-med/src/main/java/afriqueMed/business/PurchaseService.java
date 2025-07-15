package afriqueMed.business;

import afriqueMed.domain.Purchase;
import afriqueMed.infra.equipmentrepos.PurchaseRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PurchaseService {

    @Inject
    PurchaseRepository purchaseRepository;

    @Transactional
    public void createPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    public Purchase getPurchase(Long id) {
        return purchaseRepository.findById(id);
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Transactional
    public void deletePurchase(Long id) {
        Purchase purchase = purchaseRepository.findById(id);
        if (purchase != null) {
            purchaseRepository.delete(purchase);
        }
    }
}
