package org.ncapas.canchitas.Service;

import org.ncapas.canchitas.DTOs.request.ReservaRequestDTO;
import org.ncapas.canchitas.DTOs.response.ReservaResponseDTO;

import java.util.List;


public interface ReservaService {
    List<ReservaResponseDTO> findAll();
    ReservaResponseDTO findById(int id);
    ReservaResponseDTO save(ReservaRequestDTO reserva);
    void delete(int id);
}
