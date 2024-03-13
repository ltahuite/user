package com.luistahuite.user.controller;

import com.luistahuite.user.common.UserRequestMapper;
import com.luistahuite.user.common.UserResponseMapper;
import com.luistahuite.user.dto.UserRequest;
import com.luistahuite.user.dto.UserResponse;
import com.luistahuite.user.entities.User;
import com.luistahuite.user.repository.UserRepository;
import com.luistahuite.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Tag(name = "REST User API", description = "This API serve all functionality for manager users.")
public class UserRestController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    private final UserRequestMapper userRequestMapper;


    @Autowired
    public UserRestController(UserService userService, UserRepository userRepository, UserResponseMapper userResponseMapper, UserRequestMapper userRequestMapper) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userResponseMapper = userResponseMapper;
        this.userRequestMapper = userRequestMapper;
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> findAll() {
        List<User> users = userService.findAll();
        if (null == users || users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(userResponseMapper.UserListToUserResponseList(users));
        }
    }

    @Operation(description = "User creator.", summary = "return 201 in success case.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Exito."),
            @ApiResponse(responseCode = "500", description = "Internal error.")})
    @PostMapping()
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        User save = userService.save(userRequestMapper.UserRequestToUser(userRequest));
        return new ResponseEntity<>(userResponseMapper.UserToUserResponse(save), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User updateUser = optionalUser.get();
            updateUser.setName(userRequest.getName());
            updateUser.setEmail(userRequest.getEmail());
            updateUser.setPassword(userRequest.getPassword());
            User save = userRepository.save(updateUser);
            return new ResponseEntity<>(userResponseMapper.UserToUserResponse(save), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
