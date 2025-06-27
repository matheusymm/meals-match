package com.ufscar.pooa.backend.model;

import com.ufscar.pooa.backend.enums.UserEnum;

import jakarta.persistence.Entity;

@Entity
public class CommonUser extends User {
    public CommonUser(String password, String email, String name, String phone) {
        super(password, email, name, phone, UserEnum.COMMON);
    }

    public CommonUser() {
        super();
    }
}
