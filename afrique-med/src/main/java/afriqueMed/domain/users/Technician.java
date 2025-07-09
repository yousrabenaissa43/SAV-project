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
}
