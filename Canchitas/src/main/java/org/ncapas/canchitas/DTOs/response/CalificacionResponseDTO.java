package org.ncapas.canchitas.DTOs.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalificacionResponseDTO {
    private Integer idCalificacion;
    private Integer puntaje;
    private String usuarioNombre;
    private String canchaNombre;
}
