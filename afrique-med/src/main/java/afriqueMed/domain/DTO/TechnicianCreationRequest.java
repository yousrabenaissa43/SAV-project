package afriqueMed.domain.DTO;

import afriqueMed.domain.CountryEnum;
import afriqueMed.domain.Speciality;

import java.util.List;

public class TechnicianCreationRequest {
    public String keycloakId;
    public String name;
    public String phone;
    public String cin;
    public List<Speciality> specialties;
    public CountryEnum location;
}