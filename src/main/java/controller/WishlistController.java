package controller;

import entity.WishlistItem;
import service.WishlistService;

import java.util.List;

import static spark.Spark.*;

public class WishlistController {
    public WishlistController(final WishlistService wishlistService) {
        path("/api", () -> {
            path("/wishlist", () -> {
                post("", (request, response) -> {
                    String retorno;
                    WishlistItem wishlistItem = wishlistService.addItem(Integer.parseInt(request.queryParams("user_id")), request.queryParams("product_id"));
                    if(wishlistItem != null) {
                        response.status(201);
                        retorno = "Produto adicionado aos favoritos";
                    }
                    else {
                        response.status(400);
                        retorno = "Não foi possível adicionar produto aos favoritos";
                    }
                    return retorno;
                });
                get("/:id", (request, response) -> {
                    String retorno;
                    WishlistItem wishlistItem = wishlistService.getItem(Integer.parseInt(request.params("id")));
                    if(wishlistItem != null) {
                        response.status(200);
                        retorno = String.format("Item\n id: %s, Cliente: %s",
                                wishlistItem.getId(), wishlistItem.getCustomerId());
                    }
                    else {
                        response.status(400);
                        retorno = "Item não existente na lista de favoritos";
                    }
                    return retorno;
                });
                delete("/:id", (request, response) -> {
                    String retorno;
                    wishlistService.deleteItem(Integer.parseInt(request.params("id")));
                    if(wishlistService.getItem(Integer.parseInt(request.params("id"))) == null) {
                        response.status(200);
                        retorno = "Item excluído com sucesso";
                    }
                    else {
                        response.status(404);
                        retorno = "Não foi possível excluir item";
                    }
                    return retorno;
                });
                get("/user/:id", (request, response) -> {
                    List<WishlistItem> wishlistItemsList = wishlistService.findAllItemsForCustomer(Integer.parseInt(request.params("id")));
                    String retorno = "Favoritos\n";
                    for (WishlistItem wishlistItem : wishlistItemsList){
                        retorno = retorno.concat(String.format("Produto: %s\n", wishlistItem.getProductId()));
                    }
                    return retorno;
                });
            });
        });
    }
}
