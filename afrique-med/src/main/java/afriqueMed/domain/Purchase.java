package afriqueMed.domain;

import afriqueMed.domain.equipement.Item;
import afriqueMed.domain.users.Client;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Client client;

    @OneToOne(optional = false)
    private Item item;

    private LocalDate purchaseDate;

    private LocalDate warrantyEndDate;

    @Lob
    private String interventionSheet;  // Storing the file as binary

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getWarrantyEndDate() {
        return warrantyEndDate;
    }

    public void setWarrantyEndDate(LocalDate warrantyEndDate) {
        this.warrantyEndDate = warrantyEndDate;
    }

    public String getInterventionSheet() {
        return interventionSheet;
    }

    public void setInterventionSheet(String interventionSheet) {
        this.interventionSheet = interventionSheet;
    }
}