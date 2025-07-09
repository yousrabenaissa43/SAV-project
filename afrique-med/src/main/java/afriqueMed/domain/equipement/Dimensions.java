package afriqueMed.domain.equipement;

import jakarta.persistence.Embeddable;

@Embeddable
public class Dimensions {
    private float grossWeight;
    private float netWeight;
    private float volume;
    private float length;
    private float width;
    private float height;

}

