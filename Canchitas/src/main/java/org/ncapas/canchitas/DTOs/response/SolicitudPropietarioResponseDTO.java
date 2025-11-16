package org.ncapas.canchitas.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SolicitudPropietarioResponseDTO {
    private Integer idSolicitud;
    private String nombreCompleto;
    private String nombreLugar;
    private String estadoSolicitud;
    private String fechaSolicitud;
}
