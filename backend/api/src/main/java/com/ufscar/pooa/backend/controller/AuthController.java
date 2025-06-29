package com.ufscar.pooa.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufscar.pooa.backend.dto.Auth.AuthResponseDTO;
import com.ufscar.pooa.backend.dto.Auth.LoginDTO;
import com.ufscar.pooa.backend.dto.Auth.SignupDTO;
import com.ufscar.pooa.backend.service.interfaces.IAuthService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User logged in successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
    })
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO response = authService.login(loginDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
    })
    public ResponseEntity<AuthResponseDTO> signup(@Valid @RequestBody SignupDTO signupDTO) {
        AuthResponseDTO response = authService.signup(signupDTO);
        return ResponseEntity.status(201).body(response);
    }
}
