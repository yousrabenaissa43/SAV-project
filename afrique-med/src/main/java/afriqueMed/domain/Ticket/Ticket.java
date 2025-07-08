package afriqueMed.domain.Ticket;

import afriqueMed.domain.CountryEnum;
import afriqueMed.domain.Purchase;

import java.util.Date;

public class Ticket {
    private Long id;
    private TicketType ticketType;
    private Purchase purchase;
    private String location;
    private String description;
    private Priority priority;
    private Date date;
    private CountryEnum country;
    private Status status;


}