package org.ncapas.canchitas.DTOs.request;

import lombok.Data;

@Data
public class CalificacionRequest {
    private Integer idUsuario;
    private Integer idCancha;
    private Integer puntaje;
}
