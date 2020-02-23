package service;

import entity.User;

import java.util.List;

public interface UserService {
    User addUser(String name, String email);
    User getUser(int id);
    List<User> getAllUsers();
    void removeUser(int id);
}
