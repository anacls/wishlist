import controller.UserController;
import service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        new UserController(new UserServiceImpl());
    }
}
