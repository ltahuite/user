package com.luistahuite.user.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
@Schema(name = "Regex", description = "Model represents a Regex.")
public class Regex {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "CHAR(36)")
    @Schema(name = "id", example = "979942b2-8697-48c8-b231-62f0722300ef", defaultValue = "", description = "Id del Usuario")
    private UUID id;
    private String type;
    private String expression;

}
