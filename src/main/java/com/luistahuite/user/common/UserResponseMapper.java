//package com.luistahuite.user.common;
//import com.luistahuite.user.dto.UserResponse;
//import com.luistahuite.user.entities.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserResponseMapper {
//    public User UserResponseToUser(UserResponse userResponse) {
//        User user = new User();
//        user.setName(userResponse.getName());
//        user.setEmail(userResponse.getEmail());
//        user.setPassword(userResponse.getPassword());
//        return user;
//    }
//    List<User> UserResponseListToUserList(List<UserResponse> userResponses) {
//        List<User> users = new ArrayList<>();
//        userResponses.forEach(ur -> {
//            User user = new User();
//            user.setName(ur.getName());
//            user.setEmail(ur.getEmail());
//            user.setPassword(ur.getPassword());
//
//            users.add(user);
//        });
//
//        return users;
//    }
//    public UserResponse UserToUserResponse(User user) {
//        UserResponse userResponse = new UserResponse();
//        userResponse.setName(user.getName());
//        userResponse.setEmail(user.getEmail());
//        userResponse.setPassword(user.getPassword());
//        return userResponse;
//    }
//    public List<UserResponse> UserListToUserResponseList(List<User> users) {
//        List<UserResponse> userResponses = new ArrayList<>();
//        users.forEach(user -> {
//            UserResponse userResponse = new UserResponse();
//            userResponse.setName(user.getName());
//            userResponse.setEmail(user.getEmail());
//            userResponse.setPassword(user.getPassword());
//
//            userResponses.add(userResponse);
//        });
//
//        return userResponses;
//    }
//}
