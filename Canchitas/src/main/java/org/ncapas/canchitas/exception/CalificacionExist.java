package org.ncapas.canchitas.exception;

public class CalificacionExist extends RuntimeException {
    public CalificacionExist() {
        super("El usuario ya calificó esta cancha.");
    }
}
