package controller;

import entity.WishlistItem;
import service.WishlistService;
import service.WishlistServiceImpl;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.List;

class WishListApi {
    private WishlistService wishlistService = new WishlistServiceImpl();

    String addItemToWishlist(Request request, Response response) throws IOException {
        WishlistItem wishlistItem = wishlistService.addItem(Integer.parseInt(request.queryParams("user_id")), request.queryParams("product_id"));
        if(wishlistItem != null) {
            response.status(201);
            return "Produto adicionado aos favoritos";
        }
        else {
            response.status(400);
            return "Não foi possível adicionar produto aos favoritos";
        }
    }

    String getItemFromWishlist(Request request, Response response) {
        WishlistItem wishlistItem = wishlistService.getItem(Integer.parseInt(request.params("id")));
        if(wishlistItem != null) {
            response.status(200);
            return String.format("Item\n id: %s, Cliente: %s",
                    wishlistItem.getId(), wishlistItem.getCustomerId());
        }
        else {
            response.status(400);
            return "Item não existente na lista de favoritos";
        }
    }

    String deleteItemFromWishlist(Request request, Response response) {
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
    }

    String findAllItemsFromAWishlist(Request request) {
        List<WishlistItem> wishlistItemsList = wishlistService.findAllItemsForCustomer(Integer.parseInt(request.params("id")));
        String retorno = "Favoritos\n";

        for (WishlistItem wishlistItem : wishlistItemsList) {
            retorno = retorno.concat(String.format("Produto: %s\n", wishlistItem.getProductId()));
        }
        return retorno;
    }
}
