package afriqueMed.business;

import afriqueMed.domain.DTO.PurchaseDTO;
import afriqueMed.domain.Purchase;
import afriqueMed.domain.equipement.Item;
import afriqueMed.domain.users.Client;
import afriqueMed.infra.equipmentrepos.ItemRepository;
import afriqueMed.infra.equipmentrepos.PurchaseRepository;
import afriqueMed.infra.usersRepos.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PurchaseService {

    @Inject
    PurchaseRepository purchaseRepository;
    @Inject
    ClientRepository clientRepository;
    @Inject
    ItemRepository itemRepository;

    @Transactional
    public void createPurchase(PurchaseDTO dto) {
        Client client = clientRepository.findById(dto.clientId()); // Assume you have this
        Item item = itemRepository.findById(dto.itemId());         // Assume you have this

        if (client == null || item == null) {
            throw new IllegalArgumentException("Invalid client or item ID");
        }

        Purchase purchase = new Purchase();
        purchase.setClient(client);
        purchase.setItem(item);
        purchase.setPurchaseDate(dto.purchaseDate());
        purchase.setWarrantyEndDate(dto.warrantyEndDate());
        purchase.setInterventionSheet(dto.interventionSheet());

        purchaseRepository.save(purchase);
    }


    public Purchase getPurchase(Long id) {
        return purchaseRepository.findById(id);
    }
    @Transactional
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
