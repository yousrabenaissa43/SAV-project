package afriqueMed.domain;

import afriqueMed.domain.equipement.Item;
import afriqueMed.domain.users.Client;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Client client;

    @ManyToOne(optional = false)
    private Item item;

    private LocalDate purchaseDate;

    private LocalDate warrantyEndDate;

    @Lob
    private byte[] interventionSheet;  // Storing the file as binary


}