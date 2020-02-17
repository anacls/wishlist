package dao;

import entity.User;

public interface UserDAO {
    User findUserByEmail(String email);
    User findUserById(int id);
    User addNewUser(String name, String email, String role);
}
