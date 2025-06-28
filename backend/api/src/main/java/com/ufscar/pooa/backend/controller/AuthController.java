package com.ufscar.pooa.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufscar.pooa.backend.dto.LoginDTO;
import com.ufscar.pooa.backend.dto.SignupDTO;
import com.ufscar.pooa.backend.dto.User.UserCreateDTO;
import com.ufscar.pooa.backend.dto.User.UserDetailDTO;
import com.ufscar.pooa.backend.enums.UserEnum;
import com.ufscar.pooa.backend.service.interfaces.IUserService;
import com.ufscar.pooa.backend.util.JwtUtil;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User logged in successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid email or password"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access")
    })
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        if (loginDTO.email() == null || loginDTO.password() == null) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        UserDetailDTO user = userService.getUserByEmail(loginDTO.email());
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        if (!passwordEncoder.matches(loginDTO.password(), user.password())) {
            return ResponseEntity.status(403).body("Unauthorized access");
        }

        String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/signup")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user data"),
            @ApiResponse(responseCode = "409", description = "Email already registered")
    })
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO) {
        if (signupDTO.name() == null || signupDTO.email() == null || signupDTO.password() == null
                || signupDTO.phone() == null) {
            return ResponseEntity.badRequest().body("Invalid user data");
        }
        UserDetailDTO user = userService.getUserByEmail(signupDTO.email());

        if (user != null) {
            return ResponseEntity.status(409).body("Email already registered");
        }

        UserCreateDTO userDTO = new UserCreateDTO(
                signupDTO.name(),
                signupDTO.email(),
                passwordEncoder.encode(signupDTO.password()),
                signupDTO.phone(),
                UserEnum.ROLE_COMMON);
        user = userService.createUser(userDTO);
        String token = jwtUtil.generateToken(user);

        return ResponseEntity.status(201).body(token);
    }
}
