package com.ufscar.pooa.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.UserDTO;
import com.ufscar.pooa.backend.model.User;
import com.ufscar.pooa.backend.repository.UserRepository;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();

        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setName(userDTO.name());
        user.setPhone(userDTO.phone());

        userRepository.save(user);
        return new UserDTO(user.getUsername(), user.getEmail(), user.getName(), user.getPhone());
    }

    @Override
    public UserDTO updateUser(UUID userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setName(userDTO.name());
        user.setPhone(userDTO.phone());
        userRepository.save(user);

        return new UserDTO(user.getUsername(), user.getEmail(), user.getName(), user.getPhone());
    }

    @Override
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(userId);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return new UserDTO(user.getUsername(), user.getEmail(), user.getName(), user.getPhone());
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return new UserDTO(user.getUsername(), user.getEmail(), user.getName(), user.getPhone());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }

        return new ArrayList<>(users.stream()
                .map(user -> new UserDTO(user.getUsername(), user.getEmail(), user.getName(), user.getPhone()))
                .toList());
    }
}
