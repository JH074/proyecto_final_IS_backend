package org.ncapas.canchitas.DTOs.request;


import lombok.Data;

@Data
public class CanchaUpdateRequestDTO {
    private Integer idCancha;
    private String nombre;
    private String foto;
    private Integer numeroCancha;
    private String tipoCancha;
    private String jornada;
    private String lugar;
}
