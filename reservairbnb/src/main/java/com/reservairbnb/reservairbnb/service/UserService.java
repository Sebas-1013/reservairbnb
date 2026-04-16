package com.reservairbnb.reservairbnb.service;

import com.reservairbnb.reservairbnb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reservairbnb.reservairbnb.model.User;

import java.util.List;

@Service
public class UserService {

        @Autowired
        private UserRepository userRepository;

//         Método para crear un usuario (POST)
        public User guardarUsuario(User user) {
            return userRepository.save(user);
        }

//         Método para ver todos los usuarios (GET)
        public List<User> obtenerTodos() {
            return userRepository.findAll();
        }
}
