import controller.UserController;
import controller.WishlistController;
import service.UserServiceImpl;
import service.WishlistServiceImpl;

public class Main {
    public static void main(String[] args) {
        new UserController(new UserServiceImpl());
        new WishlistController(new WishlistServiceImpl());
    }
}
