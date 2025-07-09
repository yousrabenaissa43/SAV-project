package afriqueMed.domain.historyLog;

import afriqueMed.domain.Ticket.Intervention;
import afriqueMed.domain.Ticket.Ticket;
import afriqueMed.domain.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HistoryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    private Ticket ticket;

    @ManyToOne(optional = true)
    private Intervention intervention;

    @ManyToOne(optional = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private ActionType action;

    @Column(length = 1000)
    private String details;

    @Column(length = 1000)
    private String logMessage;

    private LocalDateTime timestamp;

}
