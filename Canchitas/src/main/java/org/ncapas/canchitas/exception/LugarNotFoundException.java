package org.ncapas.canchitas.exception;

public class LugarNotFoundException extends RuntimeException {
    public LugarNotFoundException(String message) {
        super(message);
    }

    public LugarNotFoundException() {
        super("Lugar no encontrado");
    }

    public LugarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
