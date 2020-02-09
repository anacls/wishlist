import service.UserService;
import service.UserServiceImpl;

import static spark.Spark.*;

public class Hello {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        path("/api", () -> {
            path("/user", () -> {
                post("/add", (request, response) -> {
                    return userService.addUser(request.queryParams("name"), request.queryParams("email"));
                });
            });
        });
    }
}
