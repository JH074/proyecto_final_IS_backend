package org.ncapas.canchitas.DTOs.request;

import lombok.*;

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
    private String nit;
    private String telefonoLugar;

    private Integer idUsuario; // viene del cliente logueado
}

