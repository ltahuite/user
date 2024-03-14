package com.luistahuite.user.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Auth", description = "Model represents a Auth.")
public class Auth {
    private String user;
    private String password;
    private String token;

}
