package com.ufscar.pooa.backend.model;

import com.ufscar.pooa.backend.enums.UserEnum;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin(String password, String email, String name, String phone) {
        super(password, email, name, phone, UserEnum.ADMIN);
    }

    public Admin() {
        super();
    }
}
