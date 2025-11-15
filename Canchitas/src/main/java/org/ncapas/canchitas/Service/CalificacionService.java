package org.ncapas.canchitas.Service;

import org.ncapas.canchitas.DTOs.request.CalificacionRequestDTO;
import org.ncapas.canchitas.DTOs.response.CalificacionResponseDTO;

public interface CalificacionService {
    CalificacionResponseDTO crearCalificacion(CalificacionRequestDTO request);
    Double obtenerPromedioDeCancha(Integer idCancha);
}
