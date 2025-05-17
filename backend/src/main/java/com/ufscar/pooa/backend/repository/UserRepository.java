package com.ufscar.pooa.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufscar.pooa.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    void createUser(String username, String password, String email, String name, String phone);

    void updateUser(UUID userId, String username, String password, String email, String name, String phone);

    void deleteUser(UUID userId);

    Optional<User> findById(UUID id);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();
}
