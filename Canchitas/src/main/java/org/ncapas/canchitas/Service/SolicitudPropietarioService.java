package org.ncapas.canchitas.Service;

import org.ncapas.canchitas.DTOs.request.SolicitudPropietarioRequestDTO;
import org.ncapas.canchitas.DTOs.response.EstadoSolicitudUsuarioDTO;
import org.ncapas.canchitas.DTOs.response.SolicitudPropietarioResponseDTO;

import java.util.List;

public interface SolicitudPropietarioService {

    SolicitudPropietarioResponseDTO crearSolicitud(SolicitudPropietarioRequestDTO dto);

    List<SolicitudPropietarioResponseDTO> listarSolicitudes();

    SolicitudPropietarioResponseDTO obtenerPorId(Integer idSolicitud);


    void aprobarSolicitud(Integer idSolicitud);

    EstadoSolicitudUsuarioDTO obtenerEstadoPorUsuario(Integer idUsuario);
    void rechazarSolicitud(Integer idSolicitud, String motivo);  // 👈 CAMBIADO

}


