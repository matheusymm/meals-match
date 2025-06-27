package com.ufscar.pooa.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.UserDTO;
import com.ufscar.pooa.backend.enums.UserEnum;
import com.ufscar.pooa.backend.model.User;
import com.ufscar.pooa.backend.repository.UserRepository;
import com.ufscar.pooa.backend.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();

        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setPhone(userDTO.phone());
        user.setRole(UserEnum.COMMON);
        user.setCreatedAt(new Date());

        userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getPhone(),
                user.getRole());
    }

    @Override
    public UserDTO updateUser(UUID userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElse(null);

        user.setEmail(userDTO.email());
        user.setName(userDTO.name());
        user.setPhone(userDTO.phone());
        userRepository.save(user);

        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getPhone(),
                user.getRole());
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.findById(userId).orElse(null);

        userRepository.deleteById(userId);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return null;
        }

        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getPhone(),
                user.getRole());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return new ArrayList<>(users.stream()
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(),
                        user.getPhone(),
                        user.getRole()))
                .toList());
    }
}
