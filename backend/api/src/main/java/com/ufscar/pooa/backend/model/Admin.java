package com.ufscar.pooa.backend.model;

import com.ufscar.pooa.backend.enums.UserEnum;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin(String username, String password, String email, String name, String phone) {
        super(username, password, email, name, phone, UserEnum.ADMIN);
    }

    public Admin() {
        super();
    }
}
