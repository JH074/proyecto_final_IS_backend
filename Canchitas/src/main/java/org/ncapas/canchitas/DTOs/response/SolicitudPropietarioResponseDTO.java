package org.ncapas.canchitas.DTOs.response;

import lombok.Builder;
import lombok.Data;
import java.util.Date;


@Data
@Builder
public class SolicitudPropietarioResponseDTO {

    private Integer idSolicitud;

    private String nombreCompleto;
    private String direccion;
    private String dui;
    private String telefono;
    private String correo;

    private String nombreLugar;
    private String direccionLugar;
    private String nit;
    private String telefonoLugar;
    private String zona;

    private Date fechaSolicitud;
    private String estadoSolicitud;
    private String motivoRechazo;
    private Integer idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
}

