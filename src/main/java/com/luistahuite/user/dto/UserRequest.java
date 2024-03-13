package com.luistahuite.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "UserRequest", description = "Model represents a UserRequest.")
@Data
public class UserRequest {
    private long id;
    private String name;
    private String email;
    private String password;
}
