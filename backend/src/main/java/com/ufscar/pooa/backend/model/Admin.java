package com.ufscar.pooa.backend.model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin(String username, String password, String email, String name, String phone) {
        super(username, password, email, name, phone, "admin");
    }

    public Admin() {
        super();
    }
}
