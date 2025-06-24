package com.ufscar.pooa.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufscar.pooa.backend.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Category findByName(String name);

}