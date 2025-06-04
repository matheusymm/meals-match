package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.UserDTO;

public interface IUserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UUID userId, UserDTO userDTO);

    void deleteUser(UUID userId);

    UserDTO getUserByUsername(String username);

    UserDTO getUserByEmail(String email);

    List<UserDTO> getAllUsers();
}
