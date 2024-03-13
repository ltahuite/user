package com.luistahuite.user.common;

import com.luistahuite.user.dto.UserRequest;
import com.luistahuite.user.entities.Phone;
import com.luistahuite.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class UserRequestMapper {
    @Autowired
    PhoneRequestMapper phoneRequestMapper;

    public User UserRequestToUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
//        List<Phone> phones = phoneRequestMapper.PhoneListRequestToPhoneList(userRequest.getPhones());
//        user.setPhones(phones);
        return user;
    }
    public List<User> UserRequestListToUserList(List<UserRequest> userRequest) {
        List<User> users = new ArrayList<>();
        userRequest.forEach(ur -> {
            User user = new User();
            user.setName(ur.getName());
            user.setEmail(ur.getEmail());
            user.setPassword(ur.getPassword());

            users.add(user);
        });

        return users;
    }

    public UserRequest UserToUserRequest(User user) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(user.getName());
        userRequest.setEmail(user.getEmail());
        userRequest.setPassword(user.getPassword());
        return userRequest;
    }
    public List<UserRequest> UserListToUserRequestList(List<User> users) {
        List<UserRequest> userRequests = new ArrayList<>();
        users.forEach(user -> {
            UserRequest userRequest = new UserRequest();
            userRequest.setName(user.getName());
            userRequest.setEmail(user.getEmail());
            userRequest.setPassword(user.getPassword());

            userRequests.add(userRequest);
        });

        return userRequests;
    }
}
