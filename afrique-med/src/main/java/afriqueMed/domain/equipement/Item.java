package afriqueMed.domain.equipement;

import afriqueMed.domain.Speciality;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private BasicInfo basicinfo;

    @Embedded
    private Dimensions dimensions;

    private LocalDateTime installationDate;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @Enumerated(EnumType.STRING)
    private Speciality itemResponsibleSpeciality;

}