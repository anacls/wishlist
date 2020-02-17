package dao;

import entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsersByEmail(String email);
    User addNewUser(String name, String email, String role);
}
