package org.ncapas.canchitas.utils.mappers;

import org.ncapas.canchitas.entities.Jornada;
import org.ncapas.canchitas.DTOs.response.JornadaResponseDTO;

import java.util.List;

public class JornadaMapper {

    /** Convierte una entidad Jornada a su DTO de respuesta */
    public static JornadaResponseDTO toDTO(Jornada entidad) {
        return JornadaResponseDTO.builder()
                .idJornada(entidad.getIdJornada())
                .semana(entidad.getSemana().getDia().toString())
                .horaInicio(entidad.getHoraInicio().toString())
                .horaFin(entidad.getHoraFin().toString())
                .precioPorHora(entidad.getPrecioPorHora())
                .estadoDisponibilidad(entidad.getEstadoDisponibilidad().getEstado().toString())
                .build();
    }

    /** Lista de Jornadas → lista de DTOs */
    public static List<JornadaResponseDTO> toDTOList(List<Jornada> entidades) {
        return entidades.stream()
                .map(JornadaMapper::toDTO)
                .toList();
    }
}
