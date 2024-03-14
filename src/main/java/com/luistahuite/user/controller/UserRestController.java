package com.luistahuite.user.controller;

import com.luistahuite.user.common.RegexRequestMapper;
import com.luistahuite.user.dto.RegexRequest;
import com.luistahuite.user.entities.Regex;
import com.luistahuite.user.exception.RuleException;
import com.luistahuite.user.common.UserResponseMapper;
import com.luistahuite.user.dto.UserRequest;
import com.luistahuite.user.dto.UserResponse;
import com.luistahuite.user.entities.User;
import com.luistahuite.user.service.RegexService;
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
    private final UserResponseMapper userResponseMapper;
    private final RegexService regexService;
    private final RegexRequestMapper regexRequestMapper;


    @Autowired
    public UserRestController(UserService userService, UserResponseMapper userResponseMapper, RegexService regexService, RegexRequestMapper regexRequestMapper) {
        this.userService = userService;
        this.userResponseMapper = userResponseMapper;
        this.regexService = regexService;
        this.regexRequestMapper = regexRequestMapper;
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
            @ApiResponse(responseCode = "500", description = "Internal error."),
            @ApiResponse(responseCode = "412", description = "Error validando datos.")})
    @PostMapping()
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) throws RuleException {
        Optional<User> validateEmail = userService.findByEmail(userRequest.getEmail());
        if (validateEmail.isPresent()) {
            throw new RuleException("El correo ya registrado.", HttpStatus.PRECONDITION_FAILED);
        } else if (!userService.validateRegexEmail(userRequest.getEmail())) {
            throw new RuleException("El correo ingresado no cumple con el formato requerido.", HttpStatus.PRECONDITION_FAILED);
        } else if (!userService.validateRegexPassword(userRequest.getPassword())) {
            throw new RuleException("El password ingresado no cumple con el formato requerido.", HttpStatus.PRECONDITION_FAILED);
        } else {
            User save = userService.save(userRequest);
            return new ResponseEntity<>(userResponseMapper.UserToUserResponse(save), HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
        Optional<User> optionalUser = userService.update(id, userRequest);
        return optionalUser.map(user -> new ResponseEntity<>(userResponseMapper.UserToUserResponse(user), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(description = "Set Regular expression.", summary = "Type values: email, password.")
    @PostMapping("/setregex")
    public ResponseEntity<?> setRegex(RegexRequest regexRequest) {
        Optional<Regex> optionalRegex = regexService.findByType(regexRequest.getType());
        Regex regex = regexRequestMapper.RegexRequestToRegex(regexRequest);
        optionalRegex.ifPresent(value -> regex.setId(value.getId()));

        regexService.save(regex);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
