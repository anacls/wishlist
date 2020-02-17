package controller;
import entity.User;
import service.UserService;
import static spark.Spark.*;

public class UserController {
    public UserController(final UserService userService) {
        path("/api", () -> {
            path("/user", () -> {
                post("/create", (request, response) -> {
                    String retorno;
                    User user = userService.addUser(request.queryParams("name"), request.queryParams("email"));
                    if(user != null){
                        response.status(201);
                        retorno = "Usuário criado com sucesso";
                    }
                    else{
                        response.status(400);
                        retorno = "Não foi possível criar um novo usuário, usuário já existente";
                    }
                    return retorno;
                });
            });
        });
    }
}
