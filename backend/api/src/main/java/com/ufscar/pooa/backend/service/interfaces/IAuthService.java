package com.ufscar.pooa.backend.service.interfaces;

import com.ufscar.pooa.backend.dto.Auth.AuthResponseDTO;
import com.ufscar.pooa.backend.dto.Auth.LoginDTO;
import com.ufscar.pooa.backend.dto.Auth.SignupDTO;

public interface IAuthService {
    AuthResponseDTO login(LoginDTO loginDTO);

    AuthResponseDTO signup(SignupDTO signupDTO);
}
