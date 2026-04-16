package com.reservairbnb.reservairbnb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.reservairbnb.reservairbnb.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Aquí Spring hace toda la magia de guardar y buscar solita
}
