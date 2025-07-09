package afriqueMed.domain.Ticket;

import afriqueMed.domain.users.Technician;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private Ticket ticket;

    @ManyToOne(optional = false)
    private Technician technician;

    private boolean isDone = false;
    private boolean setItemToBeDecommissioned = false;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Column(length = 1000)
    private String technicianNotes;

    @ElementCollection
    @Lob
    private List<byte[]> attachments;
}
