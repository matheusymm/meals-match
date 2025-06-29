package com.ufscar.pooa.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufscar.pooa.backend.dto.Auth.AuthResponseDTO;
import com.ufscar.pooa.backend.dto.Auth.LoginDTO;
import com.ufscar.pooa.backend.dto.Auth.SignupDTO;
import com.ufscar.pooa.backend.dto.User.UserCreateDTO;
import com.ufscar.pooa.backend.dto.User.UserDetailDTO;
import com.ufscar.pooa.backend.enums.UserEnum;
import com.ufscar.pooa.backend.service.interfaces.IAuthService;
import com.ufscar.pooa.backend.service.interfaces.IUserService;
import com.ufscar.pooa.backend.util.JwtUtil;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        UserDetailDTO user = userService.getUserByEmail(loginDTO.email());
        if (user == null) {
            throw new RuntimeException("User not found with the provided email.");
        }

        if (!passwordEncoder.matches(loginDTO.password(), user.password())) {
            throw new RuntimeException("Invalid credentials provided.");
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponseDTO(token);
    }

    @Override
    public AuthResponseDTO signup(SignupDTO signupDTO) {
        UserDetailDTO existingUser = userService.getUserByEmail(signupDTO.email());

        if (existingUser != null) {
            throw new RuntimeException("Email already registered");
        }
        UserCreateDTO userDTO = new UserCreateDTO(
                signupDTO.name(),
                signupDTO.email(),
                passwordEncoder.encode(signupDTO.password()),
                signupDTO.phone(),
                UserEnum.ROLE_COMMON);
        UserDetailDTO user = userService.createUser(userDTO);
        String token = jwtUtil.generateToken(user);
        return new AuthResponseDTO(token);
    }
}
