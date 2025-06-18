package org.ncapas.canchitas.DTOs.request;


import lombok.Data;

@Data
public class CanchaUpdateRequestDTO {
    private Integer idCancha;
    private String nombre;
    private String foto;
    private Integer numeroCancha;
    private Integer tipoCancha;
    private Integer jornada;
    private Integer lugar;
}
