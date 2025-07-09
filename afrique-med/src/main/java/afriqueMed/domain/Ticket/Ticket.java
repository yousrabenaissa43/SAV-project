package afriqueMed.domain.Ticket;

import afriqueMed.domain.CountryEnum;
import afriqueMed.domain.Purchase;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Purchase purchase;

    private String location;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private CountryEnum country;

    @Enumerated(EnumType.STRING)
    private Status status;


}