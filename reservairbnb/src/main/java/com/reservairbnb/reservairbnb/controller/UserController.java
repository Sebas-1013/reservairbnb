package com.reservairbnb.reservairbnb.controller;

import com.reservairbnb.reservairbnb.model.User;
import com.reservairbnb.reservairbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // La dirección web para entrar

public class UserController {
    @Autowired
    private UserService userService;

    // Para ver todos los usuarios: http://localhost:8080/api/users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.obtenerTodos();
    }

    // Para crear un usuario nuevo
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.guardarUsuario(user);
    }
}
