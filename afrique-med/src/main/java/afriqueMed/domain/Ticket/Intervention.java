package afriqueMed.domain.Ticket;

import afriqueMed.domain.users.Technician;

import java.time.LocalDateTime;
import java.util.List;

public class Intervention {
    private Long id;

    private Ticket ticket;
    private Technician technician;

    private boolean isDone = false; // Defaults to false
    private boolean setItemToBeDecommissioned = false;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String technicianNotes;

    // Instead of List<File>, store either:
    private List<byte[]> attachments;
}
