package com.ufscar.pooa.backend.service.interfaces;

import java.util.List;
import java.util.UUID;

import com.ufscar.pooa.backend.dto.User.UserCreateDTO;
import com.ufscar.pooa.backend.dto.User.UserDetailDTO;

public interface IUserService {
    UserDetailDTO createUser(UserCreateDTO userCreateDTO);

    UserDetailDTO updateUser(UUID userId, UserCreateDTO userCreateDTO);

    void deleteUser(UUID userId);

    UserDetailDTO getUserByEmail(String email);

    List<UserDetailDTO> getAllUsers();
}
