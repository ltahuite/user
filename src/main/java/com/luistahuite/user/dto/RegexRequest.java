package com.luistahuite.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "RegexRequest", description = "Model represents a RegexRequest.")
@Data
public class RegexRequest {
    @Schema(name = "type", example = "email", description = "Enviar la palabra email o password para guardar el valor de la expresión regular.")
    private String type;
    @Schema(name = "expression", example = "^[0-9,$]*$", description = "Expresión regular.")
    private String expression;

}
