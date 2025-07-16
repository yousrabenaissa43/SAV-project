package afriqueMed.api;

import afriqueMed.business.UserService;
import afriqueMed.domain.DTO.UpdateUserRequest;
import afriqueMed.domain.users.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id) {
        User user = userService.getUser(id);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        userService.deleteUser(id);
        return Response.noContent().build();
    }
    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, UpdateUserRequest request) {
        User updatedUser = userService.updateUser(
                id,
                request.keycloakId(),
                request.CIN(),
                request.name(),
                request.address(),
                request.phone()
        );

        if (updatedUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedUser).build();
    }

}
