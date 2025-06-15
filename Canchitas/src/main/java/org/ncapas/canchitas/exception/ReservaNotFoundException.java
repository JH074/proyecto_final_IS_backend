package org.ncapas.canchitas.exception;


public class ReservaNotFoundException extends RuntimeException {
    public ReservaNotFoundException(String message) {
        super(message);
    }

    public ReservaNotFoundException() {
        super("Reserva no encontrada");
    }

    public ReservaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}