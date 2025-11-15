package org.ncapas.canchitas.utils.mappers;

import org.springframework.stereotype.Component;
import org.ncapas.canchitas.entities.Calificacion;
import org.ncapas.canchitas.DTOs.response.CalificacionResponseDTO;

@Component
public class CalificacionMapper {

    public CalificacionResponse toResponse(Calificacion cal) {
        return CalificacionResponse.builder()
                .idCalificacion(cal.getIdCalificacion())
                .puntaje(cal.getPuntaje())
                .usuarioNombre(cal.getUsuario().getNombre())
                .canchaNombre(cal.getCancha().getNombre())
                .build();
    }
}
