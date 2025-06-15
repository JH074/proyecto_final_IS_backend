package org.ncapas.canchitas.exception;

public class JornadaNotFoundException extends RuntimeException {
    public JornadaNotFoundException(String message) {
        super(message);
    }

    public JornadaNotFoundException() {
        super("Jornada no encontrada");
    }

    public JornadaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
