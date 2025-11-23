package org.ncapas.canchitas.Service;

import org.ncapas.canchitas.DTOs.request.LugarRequestDTO;
import org.ncapas.canchitas.DTOs.response.LugarResponseDTO;

import java.util.List;

public interface LugarService {
    List<LugarResponseDTO> findAll();
    LugarResponseDTO findById(int id);
    LugarResponseDTO save(LugarRequestDTO lugar);
    void delete(int id);
    List<LugarResponseDTO> findByPropietario(Integer idUsuario);
}
