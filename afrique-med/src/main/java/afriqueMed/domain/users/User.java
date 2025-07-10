package afriqueMed.domain.users;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keycloakId;
    private String CIN;
    private String name;
    private String address;
    private String phone;

    public Long getId() {
        return id;
    }
}
