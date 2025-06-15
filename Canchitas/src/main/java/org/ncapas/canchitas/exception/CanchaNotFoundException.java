package org.ncapas.canchitas.exception;

public class CanchaNotFoundException extends RuntimeException {
    public CanchaNotFoundException(String message) {
        super(message);
    }

    public CanchaNotFoundException() {
        super("Cancha no encontrada");
    }

    public CanchaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

