package org.ncapas.canchitas.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoSolicitudUsuarioDTO {
    private Integer idSolicitud;
    private String estadoSolicitud;
    private String motivoRechazo;
}
