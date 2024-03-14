package com.luistahuite.user.common;

import io.swagger.v3.oas.annotations.media.Schema;
import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

public class ApiExceptionResponse {
    @Schema(accessMode = READ_ONLY, required = true)
    private String mensaje;


    public ApiExceptionResponse(String mensaje) {
        super();
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
