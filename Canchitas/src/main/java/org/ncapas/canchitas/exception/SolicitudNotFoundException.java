package org.ncapas.canchitas.exception;

public class SolicitudNotFoundException extends RuntimeException {
    public SolicitudNotFoundException(Integer id) {
        super("No se encontró la solicitud con ID: " + id);
    }
}
