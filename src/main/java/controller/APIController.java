package controller;

import static spark.Spark.*;

public class APIController {

    private UserApi userApi = new UserApi();
    private WishListApi wishListApi = new WishListApi();

    public APIController() {
        path("/api", () -> {

            path("/users", () -> {
                get("/:id",  (request, response) -> userApi.getUser(request, response));
                post("", (request, response) -> userApi.addUser(request, response));
                get("", (request, response) -> userApi.getAllUsers());
                delete("/:id", (request, response) -> userApi.deleteUser(request, response));
                put("/:id", (request, response) -> userApi.updateUser(request, response));
            });

            path("/wishlist", () -> {
                post("", (request, response) -> wishListApi.addItemToWishlist(request, response));
                get("/:id", (request, response) -> wishListApi.getItemFromWishlist(request, response));
                delete("/:id", (request, response) -> wishListApi.deleteItemFromWishlist(request, response));
                get("/user/:id", (request, response) -> wishListApi.findAllItemsFromAWishlist(request));
            });
        });
    }
}
