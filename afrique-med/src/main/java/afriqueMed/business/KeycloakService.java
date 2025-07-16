package afriqueMed.business;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;

@RequestScoped
public class KeycloakService {

    @Inject
    JsonWebToken jwt;

    /**
     * Get the Keycloak ID (subject / sub claim)
     */
    public String getKeycloakId() {
        return jwt.getSubject();
    }

    /**
     * Get the username or preferred username
     */
    public String getUsername() {
        return jwt.getClaim("preferred_username");
    }

    /**
     * Get the user's email address (if included in token)
     */
    public String getEmail() {
        return jwt.getClaim("email");
    }

    /**
     * Check if user has a specific role
     */
    public boolean hasRole(String role) {
        return jwt.getGroups().contains(role);
    }
}

