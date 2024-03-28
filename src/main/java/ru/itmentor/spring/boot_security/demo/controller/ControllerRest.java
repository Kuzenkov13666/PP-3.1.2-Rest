package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerRest {
    private final UserServiceImpl userService;

    public ControllerRest(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return allUsers;
    }
    @GetMapping("/users/{id}")
    public User getUserInfo(@PathVariable Long id) {
        User user = userService.getUser(id);

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User add successfully");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> editUser(@PathVariable Long id, @RequestBody User user) {
        userService.update(id, user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User delete successfully");
    }
}
