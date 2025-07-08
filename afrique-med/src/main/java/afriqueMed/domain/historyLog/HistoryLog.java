package afriqueMed.domain.historyLog;

import afriqueMed.domain.Ticket.Intervention;
import afriqueMed.domain.Ticket.Ticket;
import afriqueMed.domain.users.User;

import java.time.LocalDateTime;

public class HistoryLog {
    private Long id;

    private Ticket ticket;              // Nullable — when related to a Ticket
    private Intervention intervention;  // Nullable — when related to an Intervention
    private User user;                  // Actor who did the action

    private ActionType action;      // Type of action
    private String details;             // Optional technical/structured info
    private String logMessage;          // Human-readable message
    private LocalDateTime timestamp;

    // Constructors, Getters, Setters, toString()
}
