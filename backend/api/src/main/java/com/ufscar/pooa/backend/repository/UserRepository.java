package com.ufscar.pooa.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufscar.pooa.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

}
