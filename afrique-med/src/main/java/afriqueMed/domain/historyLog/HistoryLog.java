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

    @ManyToOne(optional = true)
    private User user;

    @Enumerated(EnumType.STRING)
    private ActionType action;

    @Column(length = 1000)
    private String logMessage;

    private LocalDateTime timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
