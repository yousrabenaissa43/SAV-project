package afriqueMed.business;

import afriqueMed.domain.equipement.Item;
import afriqueMed.infra.equipmentrepos.ItemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ItemService {

    @Inject
    ItemRepository itemRepository;

    @Transactional
    public void createItem(Item item) {
        itemRepository.save(item);
    }

    public Item getItem(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    @Transactional
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id);
        if (item != null) {
            itemRepository.delete(item);
        }
    }
}
