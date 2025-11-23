package org.ncapas.canchitas.exception;

public class SolicitudNotFoundException extends RuntimeException {
    public SolicitudNotFoundException(Integer id) {
        super("No se encontró la solicitud con ID: " + id);
    }
    // ✅ Nuevo constructor que recibe un mensaje personalizado (String)
    public SolicitudNotFoundException(String message) {
        super(message);
    }

}
