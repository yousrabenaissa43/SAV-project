package afriqueMed.api;

import afriqueMed.business.PurchaseService;
import afriqueMed.domain.DTO.PurchaseDTO;
import afriqueMed.domain.Purchase;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/purchases")
public class PurchaseResource {

    @Inject
    PurchaseService purchaseService;

    @POST
    public Response createPurchase(PurchaseDTO dto) {
        try {
            purchaseService.createPurchase(dto);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/{id}")
    public Response getPurchase(@PathParam("id") Long id) {
        Purchase purchase = purchaseService.getPurchase(id);
        if (purchase != null) {
            return Response.ok(purchase).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePurchase(@PathParam("id") Long id) {
        purchaseService.deletePurchase(id);
        return Response.noContent().build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePurchase(@PathParam("id") Long id, PurchaseDTO dto) {
        try {
            purchaseService.updatePurchase(id, dto);
            return Response.ok().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
