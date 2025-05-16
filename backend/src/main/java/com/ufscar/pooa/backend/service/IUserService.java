package com.ufscar.pooa.backend.service;

import java.util.List;

import com.ufscar.pooa.backend.dto.UserDTO;

public interface IUserService {
    void registerUser(String username, String password, String email, String name, String phone);

    void updateUser(String userId, String username, String password, String email, String name, String phone);

    void deleteUser(String userId);

    UserDTO getUserByUsername(String username);

    UserDTO getUserByEmail(String email);

    List<UserDTO> getAllUsers();
}
