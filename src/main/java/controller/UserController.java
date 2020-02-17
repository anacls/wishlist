package controller;
import entity.User;
import service.UserService;

import java.util.List;

import static spark.Spark.*;

public class UserController {
    public UserController(final UserService userService) {
        path("/api", () -> {

            path("/users", () -> {

                get("/:id", (request, response) -> {
                    String retorno;
                    User user = userService.getUser(Integer.parseInt(request.params("id")));
                    if(user != null) {
                        response.status(200);
                        retorno = String.format("Usuário\n Nome: %s, Email: %s, Role: %s",
                                user.getName(), user.getEmail(), user.getRole());
                    }
                    else {
                        response.status(400);
                        retorno = "Usuário não existente na base de dados";
                    }
                    return retorno;
                });

                post("", (request, response) -> {
                    String retorno;
                    User user = userService.addUser(request.queryParams("name"), request.queryParams("email"));
                    if(user != null) {
                        response.status(201);
                        retorno = "Usuário criado com sucesso";
                    }
                    else {
                        response.status(400);
                        retorno = "Não foi possível criar um novo usuário, usuário já existente";
                    }
                    return retorno;
                });
                get("", (request, response) -> {
                    List<User> users = userService.getAllUsers();
                    String retorno = "Usuários\n";
                    for (User user : users){
                        retorno = retorno.concat(String.format("Nome: %s, Email: %s, Role: %s\n", user.getName(), user.getEmail(), user.getRole()));
                    }
                    return retorno;
                });
            });
        });
    }
}
