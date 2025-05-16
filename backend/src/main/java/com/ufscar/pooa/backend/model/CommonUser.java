package com.ufscar.pooa.backend.model;

import jakarta.persistence.Entity;

@Entity
public class CommonUser extends User {
    public CommonUser(String username, String password, String email, String name, String phone) {
        super(username, password, email, name, phone, "common_user");
    }

    public CommonUser() {
        super();
    }
}
