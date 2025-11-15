package org.ncapas.canchitas.Service;

import org.ncapas.canchitas.DTOs.request.CalificacionRequestDTO;
import org.ncapas.canchitas.DTOs.response.CalificacionResponseDTO;

public interface CalificacionService {
    CalificacionResponse crearCalificacion(CalificacionRequest request);
    Double obtenerPromedioDeCancha(Integer idCancha);
}
