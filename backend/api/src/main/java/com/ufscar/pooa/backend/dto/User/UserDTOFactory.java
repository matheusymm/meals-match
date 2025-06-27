package com.ufscar.pooa.backend.dto.User;

import com.ufscar.pooa.backend.model.User;

public class UserDTOFactory {

    public static UserDetailDTO toDetailDTO(User user) {
        return new UserDetailDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            null,
            user.getPhone(),
            user.getRole()
        );
    }

}
