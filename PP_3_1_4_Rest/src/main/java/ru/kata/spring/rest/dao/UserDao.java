package ru.kata.spring.rest.dao;

import ru.kata.spring.rest.models.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    User getUserById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    List<User> getAllUsers();

    User getUserByUsername(String name);
}
