package org.ncapas.canchitas.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudPropietarioRequestDTO {
    private String nombreCompleto;
    private String direccion;
    private String dui;
    private String telefono;
    private String correo;
    private String nombreLugar;
    private String direccionLugar;
    private String telefonoLugar;
    private String nit;
}
