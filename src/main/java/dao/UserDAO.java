package dao;

import entity.User;

import java.util.List;

public interface UserDAO {
    User findUserByEmail(String email);
    User findUserById(int id);
    List<User> findAll();
    User createUser(String name, String email, String role);
}
