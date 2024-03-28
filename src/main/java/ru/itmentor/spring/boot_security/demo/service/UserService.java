package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User findUserByUsername(String userName);
    List<User> getAllUsers();
    public User getUser(Long id);
    public void saveUser(User user);
    public void update(Long id, User user);
    public void delete(Long id);
}
