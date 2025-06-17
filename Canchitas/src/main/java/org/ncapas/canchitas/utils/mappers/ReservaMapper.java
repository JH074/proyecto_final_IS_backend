package org.ncapas.canchitas.utils.mappers;

import org.ncapas.canchitas.entities.Reserva;
import org.ncapas.canchitas.DTOs.response.ReservaResponseDTO;

import java.text.SimpleDateFormat;
import java.util.List;

public class ReservaMapper {

    private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyy-MM-dd");

    /** Convierte una entidad Reserva a su DTO de respuesta */
    public static ReservaResponseDTO toDTO(Reserva entidad) {
        return ReservaResponseDTO.builder()
                .idReserva(entidad.getIdReserva())
                .nombreUsuario(
                        entidad.getUsuario().getNombre() + " " +
                                entidad.getUsuario().getApellido()
                )
                .nombreCancha(entidad.getCancha().getNombre())
                .nombreLugar(entidad.getLugar().getNombre())
                .metodoPago(entidad.getMetodoPago().getMetodoPago().toString())
                .estadoReserva(entidad.getEstadoReserva().toString())
                .fechaReserva(DATE_FMT.format(entidad.getFechaReserva()))
                .fechaCreacion(DATE_FMT.format(entidad.getFechaCreacion()))
                .horaEntrada(entidad.getHoraEntrada().toString())
                .horaSalida(entidad.getHoraSalida().toString())
                .precioTotal(entidad.getPrecioTotal())
                .build();
    }

    /** Lista de Reservas → lista de DTOs */
    public static List<ReservaResponseDTO> toDTOList(List<Reserva> entidades) {
        return entidades.stream()
                .map(ReservaMapper::toDTO)
                .toList();
    }
}
