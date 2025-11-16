package org.ncapas.canchitas.utils.mappers;

import org.ncapas.canchitas.DTOs.request.SolicitudPropietarioRequestDTO;
import org.ncapas.canchitas.DTOs.response.SolicitudPropietarioResponseDTO;
import org.ncapas.canchitas.entities.SolicitudPropietario;
import org.springframework.stereotype.Component;
import org.ncapas.canchitas.entities.*;


import java.util.Date;

public class SolicitudPropietarioMapper {

    public static SolicitudPropietario toEntity(
            SolicitudPropietarioRequestDTO dto,
            Usuario usuario
    ) {
        return SolicitudPropietario.builder()
                .nombreCompleto(dto.getNombreCompleto())
                .direccion(dto.getDireccion())
                .dui(dto.getDui())
                .telefono(dto.getTelefono())
                .correo(dto.getCorreo())
                .nombreLugar(dto.getNombreLugar())
                .direccionLugar(dto.getDireccionLugar())
                .nit(dto.getNit())
                .telefonoLugar(dto.getTelefonoLugar())
                .fechaSolicitud(new Date())
                .estadoSolicitud(SolicitudPropietario.EstadoSolicitud.PENDIENTE)
                .usuario(usuario)
                .build();
    }

    public static SolicitudPropietarioResponseDTO toDTO(SolicitudPropietario s) {
        return SolicitudPropietarioResponseDTO.builder()
                .idSolicitud(s.getIdSolicitud())
                .nombreCompleto(s.getNombreCompleto())
                .direccion(s.getDireccion())
                .dui(s.getDui())
                .telefono(s.getTelefono())
                .correo(s.getCorreo())
                .nombreLugar(s.getNombreLugar())
                .direccionLugar(s.getDireccionLugar())
                .nit(s.getNit())
                .telefonoLugar(s.getTelefonoLugar())
                .zona(s.getZona().getDepartamento())
                .fechaSolicitud(s.getFechaSolicitud())
                .estadoSolicitud(s.getEstadoSolicitud().name())
                .idUsuario(s.getUsuario().getIdUsuario())
                .nombreUsuario(s.getUsuario().getNombre() + " " + s.getUsuario().getApellido())
                .correoUsuario(s.getUsuario().getCorreo())
                .build();
    }
}

