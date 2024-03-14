package com.luistahuite.user.common;
import com.luistahuite.user.dto.UserResponse;
import com.luistahuite.user.entities.User;
import com.luistahuite.user.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserResponseMapper {
    private final PhoneService phoneService;
    @Autowired
    public UserResponseMapper(PhoneService phoneService) {
        this.phoneService = phoneService;
    }


    public UserResponse userToUserResponse(User user) {
        return mapUserToUserResponse(user);
    }
    public List<UserResponse> userListToUserResponseList(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> userResponses.add(mapUserToUserResponse(user)));

        return userResponses;
    }

    private UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setPhones(phoneService.findAllByUserId(user.getId()));
        userResponse.setCreated(user.getCreated());
        userResponse.setModified(user.getModified());
        userResponse.setLastLogin(user.getLastLogin());
        userResponse.setToken(user.getToken());
        userResponse.setIsActive(user.getIsActive());

        return userResponse;
    }
}
