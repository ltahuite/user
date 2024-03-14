package com.luistahuite.user.exception;


import org.springframework.http.HttpStatus;

public class RuleException extends Exception {
    private String mensaje;
    private HttpStatus httpStatus;


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public RuleException (String mensaje, HttpStatus httpStatus) {
        super();
        this.mensaje = mensaje;
        this.httpStatus = httpStatus;
    }
}
