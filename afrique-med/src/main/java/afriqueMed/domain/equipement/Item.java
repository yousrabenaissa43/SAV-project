package afriqueMed.domain.equipement;

import afriqueMed.domain.Speciality;

import java.time.LocalDateTime;

public class Item {
    private BasicInfo basicinfo;
    private Dimensions dimensions;
    private LocalDateTime installationDate;
    private ItemStatus itemStatus;
    private Speciality itemResponsibleSpeciality;

}