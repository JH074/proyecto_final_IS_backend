package org.ncapas.canchitas.DTOs.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String role;
}