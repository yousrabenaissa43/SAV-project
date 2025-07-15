package afriqueMed.api;

import afriqueMed.business.PurchaseService;
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
    public Response createPurchase(Purchase purchase) {
        purchaseService.createPurchase(purchase);
        return Response.status(Response.Status.CREATED).entity(purchase).build();
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
}
