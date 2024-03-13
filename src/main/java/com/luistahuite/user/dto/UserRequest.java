package com.luistahuite.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "UserRequest", description = "Model represents a UserRequest.")
@Data
public class UserRequest {
    @Schema(name = "name", required = true, example = "Luis", defaultValue = "", description = "Nombre del Usuario")
    private String name;
    @Schema(name = "email", required = true, example = "luis@example.com", defaultValue = "luis@example.com", description = "Correo del Usuario.")
    private String email;
    private String password;
}
