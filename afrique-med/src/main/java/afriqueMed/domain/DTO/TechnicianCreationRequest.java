package afriqueMed.domain.DTO;

import afriqueMed.domain.CountryEnum;
import afriqueMed.domain.Speciality;

import java.util.List;

public record TechnicianCreationRequest(
        String keycloakId,
        String name,
        String phone,
        String cin,
        List<Speciality> specialties,
        CountryEnum location
) {}
