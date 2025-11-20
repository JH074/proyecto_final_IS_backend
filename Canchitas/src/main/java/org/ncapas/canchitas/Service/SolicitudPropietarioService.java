package org.ncapas.canchitas.Service;

import org.ncapas.canchitas.DTOs.request.SolicitudPropietarioRequestDTO;
import org.ncapas.canchitas.DTOs.response.SolicitudPropietarioResponseDTO;

import java.util.List;

public interface SolicitudPropietarioService {

    SolicitudPropietarioResponseDTO crearSolicitud(SolicitudPropietarioRequestDTO dto);

    List<SolicitudPropietarioResponseDTO> listarSolicitudes();

    SolicitudPropietarioResponseDTO obtenerPorId(Integer idSolicitud);

    // Nuevo: usado por el controller para convertir a PROPIETARIO
    void aprobarSolicitud(Integer idSolicitud);
}


