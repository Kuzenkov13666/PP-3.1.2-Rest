package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findByIdName(Long id);
}
