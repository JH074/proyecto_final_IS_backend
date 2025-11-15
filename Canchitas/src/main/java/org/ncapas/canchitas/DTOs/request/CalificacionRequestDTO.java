package org.ncapas.canchitas.DTOs.request;

import lombok.Data;

@Data
public class CalificacionRequestDTO {
    private Integer idUsuario;
    private Integer idCancha;
    private Integer puntaje;
}
