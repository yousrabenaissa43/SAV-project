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

    public float getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(float netWeight) {
        this.netWeight = netWeight;
    }

    public float getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(float grossWeight) {
        this.grossWeight = grossWeight;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}

