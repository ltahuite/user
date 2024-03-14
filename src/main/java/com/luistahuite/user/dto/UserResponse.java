package com.luistahuite.user.dto;

import com.luistahuite.user.entities.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Schema(name = "UserResponse", description = "Model represents a UserResponse.")
@Data
public class UserResponse {
    @Schema(name = "id", example = "979942b2-8697-48c8-b231-62f0722300ef", defaultValue = "", description = "Id del Usuario")
    private UUID id;
    @Schema(name = "name", example = "Luis", defaultValue = "", description = "Nombre del Usuario")
    private String name;
    @Schema(name = "email", example = "luis@example.com", defaultValue = "luis@example.com", description = "Correo del Usuario.")
    private String email;
    private String password;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private Boolean isActive;
    private List<Phone> phones;
}
