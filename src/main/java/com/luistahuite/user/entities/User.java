package com.luistahuite.user.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Schema(name = "User", description = "Model represents a User.")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "CHAR(36)")
    @Schema(name = "id", example = "979942b2-8697-48c8-b231-62f0722300ef", defaultValue = "", description = "Id del Usuario")
    private UUID id;
    @Schema(name = "name", example = "Luis", defaultValue = "", description = "Nombre del Usuario")
    private String name;
    @Schema(name = "email", example = "luis@example.com", defaultValue = "luis@example.com", description = "Correo del Usuario. Debe ser unico.")
    private String email;
    private String password;
    @CreationTimestamp
    private Date created;
    @CreationTimestamp
    private Date modified;
    @CreationTimestamp
    private Date lastLogin;
    private String token;
    private Boolean isActive;

}
