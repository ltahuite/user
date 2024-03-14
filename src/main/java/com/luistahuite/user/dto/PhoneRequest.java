package com.luistahuite.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luistahuite.user.entities.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Schema(name = "PhoneRequest", description = "Model represents a PhoneRequest.")
public class PhoneRequest {
    @Schema(name = "number", example = "1234567", defaultValue = "", description = "Número de teléfono")
    private Integer number;
    @Schema(name = "citycode", example = "1", defaultValue = "", description = "Código de área del teléfono")
    private Integer citycode;
    @Schema(name = "contrycode", example = "502", defaultValue = "", description = "Código de ciudad del teléfono")
    private Integer contrycode;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = User.class)
    @JoinColumn(name = "userId", nullable = true)
    private User user;
}
