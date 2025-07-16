package afriqueMed.domain.DTO;

public record UpdateUserRequest(
        String keycloakId,
        String CIN,
        String name,
        String address,
        String phone
) {}
