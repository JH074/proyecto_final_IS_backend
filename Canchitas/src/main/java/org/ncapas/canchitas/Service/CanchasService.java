package org.ncapas.canchitas.Service;

import org.ncapas.canchitas.DTOs.request.CanchaRequestDTO;
import org.ncapas.canchitas.DTOs.request.CanchaUpdateRequestDTO;
import org.ncapas.canchitas.DTOs.response.CanchasResponseDTO;
import java.util.List;

public interface CanchasService {
    List<CanchasResponseDTO> findAll();
    CanchasResponseDTO findById(int id);
    CanchasResponseDTO save(CanchaRequestDTO cancha);
    CanchasResponseDTO update(CanchaUpdateRequestDTO cancha);
    void delete(int id);
}



