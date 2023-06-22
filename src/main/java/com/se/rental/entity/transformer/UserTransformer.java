package com.se.rental.entity.transformer;

import com.se.rental.entity.User;
import com.se.rental.entity.dto.UserDto;

public class UserTransformer {

    private UserTransformer() {
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}
