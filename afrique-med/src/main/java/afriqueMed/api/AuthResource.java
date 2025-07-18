package afriqueMed.api;

import afriqueMed.business.KeycloakService;
import afriqueMed.business.UserService;
import afriqueMed.domain.users.User;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    KeycloakService keycloakService;

    @Inject
    UserService userService;

    @GET
    @Path("/me")
    //@RolesAllowed({"client", "technician", "admin"}) //use when the token is saved
    public Response getOrCreateAppUser() {
        String keycloakId = keycloakService.getKeycloakId();
        String name = keycloakService.getUsername(); // or jwt.getClaim("name")
        String email = keycloakService.getEmail();
        String phone = "00000000";

        User user = userService.getOrCreateUserFromKeycloak(keycloakId, name, email, phone);

        return Response.ok(user).build();
    }
}
