package afriqueMed.domain.users;

import afriqueMed.domain.CountryEnum;
import afriqueMed.domain.Speciality;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class Technician extends User{

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Speciality> specialities;
    private boolean isOnVacation;
    @Enumerated(EnumType.STRING)
    private CountryEnum location;

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    public boolean isOnVacation() {
        return isOnVacation;
    }

    public void setOnVacation(boolean onVacation) {
        isOnVacation = onVacation;
    }

    public CountryEnum getLocation() {
        return location;
    }

    public void setLocation(CountryEnum location) {
        this.location = location;
    }
}
