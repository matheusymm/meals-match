package com.ufscar.pooa.backend.dto.User;

import com.ufscar.pooa.backend.model.User;

public class UserDTOFactory {

    public static UserDetailDTO toDetailDTO(User user) {
        return new UserDetailDTO(
            user.getId(),
            null,
            user.getEmail(),
            user.getName(),
            user.getPhone(),
            user.getRole()
        );
    }

}
