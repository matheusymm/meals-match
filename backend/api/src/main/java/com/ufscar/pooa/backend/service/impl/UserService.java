package com.ufscar.pooa.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.User.UserDTOFactory;
import com.ufscar.pooa.backend.dto.User.UserCreateDTO;
import com.ufscar.pooa.backend.dto.User.UserDetailDTO;
import com.ufscar.pooa.backend.enums.UserEnum;
import com.ufscar.pooa.backend.model.User;
import com.ufscar.pooa.backend.repository.UserRepository;
import com.ufscar.pooa.backend.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailDTO createUser(UserCreateDTO userCreateDTO) {
        User user = new User();

        user.setPassword(userCreateDTO.password());
        user.setEmail(userCreateDTO.email());
        user.setName(userCreateDTO.name());
        user.setPhone(userCreateDTO.phone());
        user.setRole(UserEnum.COMMON);
        user.setCreatedAt(new Date());

        var newUser = userRepository.save(user);

        return UserDTOFactory.toDetailDTO(newUser);
    }

    @Override
    public UserDetailDTO updateUser(UUID userId, UserCreateDTO userCreateDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEmail(userCreateDTO.email());
        user.setName(userCreateDTO.name());
        user.setPhone(userCreateDTO.phone());

        var updatedUser = userRepository.save(user);
        return UserDTOFactory.toDetailDTO(updatedUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.findById(userId).orElse(null);

        userRepository.deleteById(userId);
    }

    @Override
    public UserDetailDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        return UserDTOFactory.toDetailDTO(user);
    }

    @Override
    public List<UserDetailDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return new ArrayList<>(users.stream()
                .map(UserDTOFactory::toDetailDTO)
                .toList());
    }
}
