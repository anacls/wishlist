package dao;

import entity.User;
import java.util.List;

public interface UserDAO {
    User findUserByEmail(String email);
    User findUserById(int id);
    User addNewUser(String name, String email, String role);
    List<User> findAll();
    void deleteUser(int id);
    User updateUser(int id, String name, String email);
}
