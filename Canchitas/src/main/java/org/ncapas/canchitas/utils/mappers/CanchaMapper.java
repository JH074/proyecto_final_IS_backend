package org.ncapas.canchitas.utils.mappers;

import org.ncapas.canchitas.entities.Cancha;
import org.ncapas.canchitas.DTOs.response.CanchasResponseDTO;

import java.util.List;

public class CanchaMapper {

    /** Convierte una entidad Cancha a su DTO de respuesta */
    public static CanchasResponseDTO toDTO(Cancha entidad) {
        return CanchasResponseDTO.builder()
                .idCancha(entidad.getIdCancha())
                .nombre(entidad.getNombre())
                .foto(entidad.getFoto())
                .numeroCancha(entidad.getNumeroCancha())
                // Enum Tipo del campo tipoCancha
                .tipoCancha(entidad.getTipoCancha().getTipo().toValue())
                // Usamos el día de la semana para representar la jornada
                .jornada(entidad.getJornada().getSemana().getDia().toString())
                // Nombre del lugar
                .lugar(entidad.getLugar().getNombre())
                .build();
    }

    /** Convierte una lista de Cancha a lista de DTOs */
    public static List<CanchasResponseDTO> toDTOList(List<Cancha> entidades) {
        return entidades.stream()
                .map(CanchaMapper::toDTO)
                .toList();
    }
}
