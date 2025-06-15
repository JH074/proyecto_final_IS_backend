package org.ncapas.canchitas.Service;

import org.ncapas.canchitas.DTOs.request.JornadaRequestDTO;
import org.ncapas.canchitas.DTOs.response.JornadaResponseDTO;

import java.util.List;

public interface JornadaService {
    List<JornadaResponseDTO> findAll();
    JornadaResponseDTO findById(int id);
    JornadaResponseDTO save(JornadaRequestDTO jornada);
    void delete(int id);
}

