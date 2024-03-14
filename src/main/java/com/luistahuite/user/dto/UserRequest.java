package com.luistahuite.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "UserRequest", description = "Model represents a UserRequest.")
@Data
public class UserRequest {
    @Schema(name = "name", example = "Luis", defaultValue = "", description = "Nombre del Usuario")
    private String name;
    @Schema(name = "email", example = "luis@example.com", defaultValue = "luis@example.com", description = "Correo del Usuario.")
    private String email;
    private String password;
    private List<PhoneRequest> phones;

}
