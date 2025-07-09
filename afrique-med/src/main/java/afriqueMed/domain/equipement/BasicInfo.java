package afriqueMed.domain.equipement;

import jakarta.persistence.Embeddable;

@Embeddable
public class BasicInfo {
    private String name;
    private String description;
    private String code;
    private String brand;
    private String model;
    private String category;
    private Long serial;
    private byte[] image;

}

