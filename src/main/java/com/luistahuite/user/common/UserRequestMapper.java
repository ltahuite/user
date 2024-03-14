package com.luistahuite.user.common;

import com.luistahuite.user.dto.UserRequest;
import com.luistahuite.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper {
    private final PhoneRequestMapper phoneRequestMapper;

    @Autowired
    public UserRequestMapper(PhoneRequestMapper phoneRequestMapper) {
        this.phoneRequestMapper = phoneRequestMapper;

    }

    public User userRequestToUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        user.setIsActive(Boolean.TRUE);

        return user;
    }

}
