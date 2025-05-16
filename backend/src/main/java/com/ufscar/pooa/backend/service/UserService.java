package com.ufscar.pooa.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.UserDTO;
import com.ufscar.pooa.backend.repository.UserRepository;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(String username, String password, String email, String name, String phone) {
        // Implement the logic to register a user
    }

    @Override
    public void updateUser(String userId, String username, String password, String email, String name, String phone) {
        // Implement the logic to update a user
    }

    @Override
    public void deleteUser(String userId) {
        // Implement the logic to delete a user
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        // Implement the logic to get a user by username
        return null;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        // Implement the logic to get a user by email
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        // Implement the logic to get all users
        return null;
    }
}
