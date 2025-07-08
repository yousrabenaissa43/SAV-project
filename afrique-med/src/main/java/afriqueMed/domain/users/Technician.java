package afriqueMed.domain.users;

import afriqueMed.domain.CountryEnum;
import afriqueMed.domain.Speciality;

import java.util.List;

public class Technician {
    private List<Speciality> specialities;
    private boolean isOnVacation;
    private CountryEnum location;
}
