package org.ncapas.canchitas.exceptions;

public class CalificacionExist extends RuntimeException {
    public CalificacionExist() {
        super("El usuario ya calificó esta cancha.");
    }
}
