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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BasicInfo getBasicinfo() {
        return basicinfo;
    }

    public void setBasicinfo(BasicInfo basicinfo) {
        this.basicinfo = basicinfo;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public LocalDateTime getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(LocalDateTime installationDate) {
        this.installationDate = installationDate;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Speciality getItemResponsibleSpeciality() {
        return itemResponsibleSpeciality;
    }

    public void setItemResponsibleSpeciality(Speciality itemResponsibleSpeciality) {
        this.itemResponsibleSpeciality = itemResponsibleSpeciality;
    }
}