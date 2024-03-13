package com.luistahuite.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "UserResponse", description = "Model represents a UserResponse.")
@Data
public class UserResponse {
    private long id;
    private String name;
    private String email;
    private String password;
}
