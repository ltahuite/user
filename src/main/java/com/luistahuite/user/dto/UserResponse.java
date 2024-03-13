package com.luistahuite.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "UserResponse", description = "Model represents a UserResponse.")
@Data
public class UserResponse {
    @Schema(name = "Id", required = true, example = "1", defaultValue = "", description = "Id del Usuario")
    private long id;
    @Schema(name = "Name", required = true, example = "Luis", defaultValue = "", description = "Nombre del Usuario")
    private String name;
    @Schema(name = "Email", required = true, example = "luis@example.com", defaultValue = "luis@example.com", description = "Correo del Usuario.")
    private String email;
    private String password;
}
