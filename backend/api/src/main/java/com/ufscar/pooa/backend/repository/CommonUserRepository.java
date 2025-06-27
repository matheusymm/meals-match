package com.ufscar.pooa.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufscar.pooa.backend.model.CommonUser;

@Repository
public interface CommonUserRepository extends JpaRepository<CommonUser, UUID> {

    CommonUser findByEmail(String email);

}
