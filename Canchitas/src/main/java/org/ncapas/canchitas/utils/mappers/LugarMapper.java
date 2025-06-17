package org.ncapas.canchitas.utils.mappers;

import org.ncapas.canchitas.entities.Lugar;
import org.ncapas.canchitas.DTOs.response.LugarResponseDTO;

import java.util.List;

public class LugarMapper {

    /** Convierte una entidad Lugar a su DTO de respuesta */
    public static LugarResponseDTO toDTO(Lugar entidad) {
        return LugarResponseDTO.builder()
                .idLugar(entidad.getIdLugar())
                .nombre(entidad.getNombre())
                .direccion(entidad.getDireccion())
                .capacidad(entidad.getCapacidad())
                .zona(entidad.getZona().getDepartamento())
                .build();
    }

    /** Lista de Lugares → lista de DTOs */
    public static List<LugarResponseDTO> toDTOList(List<Lugar> entidades) {
        return entidades.stream()
                .map(LugarMapper::toDTO)
                .toList();
    }
}
