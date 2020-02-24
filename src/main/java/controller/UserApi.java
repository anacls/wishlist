package controller;
import entity.User;
import service.UserService;
import service.UserServiceImpl;
import spark.Request;
import spark.Response;

import java.util.List;

class UserApi {
    private UserService userService = new UserServiceImpl();

    String getUser(Request request, Response response) {
        User user = userService.getUser(Integer.parseInt(request.params("id")));
        if (user != null) {
            response.status(200);
            return String.format("Usuário\n Nome: %s, Email: %s, Role: %s",
                    user.getName(), user.getEmail(), user.getRole());
        } else {
            response.status(400);
            return "Usuário não existente na base de dados";
        }
    }

    String addUser(Request request, Response response) {
        User user = userService.addUser(request.queryParams("name"), request.queryParams("email"));
        if(user != null) {
            response.status(201);
            return "Usuário criado com sucesso";
        }
        else {
            response.status(400);
            return "Não foi possível criar um novo usuário, usuário já existente";
        }
    }

    String getAllUsers() {
        List<User> users = userService.getAllUsers();
        String retorno = "Usuários\n";
        for (User user : users){
            retorno = retorno.concat(String.format("Nome: %s, Email: %s, Role: %s\n", user.getName(), user.getEmail(), user.getRole()));
        }
        return retorno;
    }

    String deleteUser(Request request, Response response) {
        userService.removeUser(Integer.parseInt(request.params("id")));
        if(userService.getUser(Integer.parseInt(request.params("id"))) == null) {
            response.status(200);
            return "Excluido com sucesso";
        }
        else {
            response.status(404);
            return "Não foi possível excluir o usuário";
        }
    }

    String updateUser(Request request, Response response) {
        User user = userService.updateUser(Integer.parseInt(request.params("id")),
                request.queryParams("name"),
                request.queryParams("email"));
        if(user != null) {
            response.status(201);
            return "Usuário atualizado com sucesso";
        }
        else {
            response.status(400);
            return "Não foi possível atualizar usuário, usuário não existe";
        }
    }
}
