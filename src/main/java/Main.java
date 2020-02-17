import controller.UserController;
import service.UserServiceImpl;

//import service.UserServiceImpl;
//import static spark.Spark.*;
//
//public class Hello {
//    public static void main(String[] args) {
//        UserServiceImpl userService = new UserServiceImpl();
//        path("/api", () -> {
//            path("/user", () -> {
//                post("/create", (request, response) -> {
//                    String retorno = userService.addUser(request.queryParams("name"), request.queryParams("email"));
//                    if(retorno.equals("Usuário criado com sucesso")) {
//                        response.status(201);
//                    }
//                    else{
//                        response.status(400);
//                    }
//                    return retorno;
//                });
//            });
//        });
//    }
//}
public class Main {
    public static void main(String[] args) {
        new UserController(new UserServiceImpl());
    }
}
