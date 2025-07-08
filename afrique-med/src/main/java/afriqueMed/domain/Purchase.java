package afriqueMed.domain;

import afriqueMed.domain.equipement.Item;
import afriqueMed.domain.users.Client;

import java.util.Date;

public class Purchase {
    private Long id;
    private Client client;                // Assume you have a Client class
    private Item item;                    // Your existing Item class
    private Date purchaseDate;
    private Date warrantyEndDate;
    private byte[] interventionSheet;     // Storing the file as binary

    // Constructors, Getters, Setters, toString()
}