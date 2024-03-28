package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleServiceImpl;
import ru.itmentor.spring.boot_security.demo.service.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleService;

    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleService) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
    }

    @GetMapping("/admin/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userServiceImpl.getAllUsers());
        return "allUsers";
    }

    @GetMapping("/admin/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "new";
    }

    @PostMapping("/admin/users/add")
    public String addUser(@ModelAttribute User user,
                          @RequestParam("roles") List<Long> rlsId) {
        Set<Role> roles = new HashSet<>();
        for (Long roleId : rlsId) {
            roles.add(roleService.findByIdName(roleId));
        }
        user.setRoles(roles);
        userServiceImpl.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userServiceImpl.getUser(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/admin/users/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute User user) {
        userServiceImpl.update(id, user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userServiceImpl.delete(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/{id}")
    public String getUserInfo(@PathVariable Long id, Model model) {
        User user = userServiceImpl.getUser(id);

        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin/user/{id}")
    public String getAdmUserInfo(@PathVariable Long id, Model model) {
        User user = userServiceImpl.getUser(id);

        model.addAttribute("user", user);
        return "admin";
    }
}
