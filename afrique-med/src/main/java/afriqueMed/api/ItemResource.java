package afriqueMed.api;

import afriqueMed.business.ItemService;
import afriqueMed.domain.equipement.Item;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
//tested
@Path("/api/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {

    @Inject
    ItemService itemService;
    private final String PATH_TO_IMAGES="C:\\Users\\Mega_PC\\Desktop\\afrique-med\\afrique-med\\src\\main\\resources\\assets\\images\\";

    @POST
    public Response createItem(Item item) {
        itemService.createItem(item);
        return Response.status(Response.Status.CREATED).entity(item).build();
    }

    @GET
    @Path("/{id}")
    public Response getItem(@PathParam("id") Long id) {
        Item item = itemService.getItem(id);
        if (item != null) {
            return Response.ok(item).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<Item> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return items;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") Long id) {
        itemService.deleteItem(id);
        return Response.noContent().build();
    }
}