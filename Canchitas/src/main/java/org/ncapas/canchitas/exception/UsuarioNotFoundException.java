package org.ncapas.canchitas.exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }

    public UsuarioNotFoundException() {
        super("Usuario no encontrado");
    }

    public UsuarioNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
