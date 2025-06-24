package com.ufscar.pooa.backend.model;

import com.ufscar.pooa.backend.enums.UserEnum;

import jakarta.persistence.Entity;

@Entity
public class CommonUser extends User {
    public CommonUser(String username, String password, String email, String name, String phone) {
        super(username, password, email, name, phone, UserEnum.COMMON);
    }

    public CommonUser() {
        super();
    }
}
