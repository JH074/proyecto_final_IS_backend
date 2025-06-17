package org.ncapas.canchitas.utils.mappers;

import org.ncapas.canchitas.entities.Usuario;
import org.ncapas.canchitas.entities.Rol;
import org.ncapas.canchitas.DTOs.request.UsuarioRequestDTO;
import org.ncapas.canchitas.DTOs.response.UsuarioResponseDTO;

import java.util.List;

public class UsuarioMapper {

    /**
     * Convierte un UsuarioRequestDTO en la entidad Usuario.
     * El ID se asigna automáticamente al persistir.
     */
    public static Usuario toEntity(UsuarioRequestDTO dto) {
        return Usuario.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .correo(dto.getCorreo())
                .contrasena(dto.getContrasena())
                // Creamos un stub de Rol con sólo el ID; el Service debe resolver la entidad completa.
                .rol(Rol.builder().idRol(dto.getRolId()).build())
                .build();
    }

    /**
     * Convierte una entidad Usuario en su DTO de respuesta.
     */
    public static UsuarioResponseDTO toDTO(Usuario entidad) {
        return UsuarioResponseDTO.builder()
                .idUsuario(entidad.getIdUsuario())
                .nombre(entidad.getNombre())
                .apellido(entidad.getApellido())
                .correo(entidad.getCorreo())
                // Extraemos el nombre del rol para el cliente
                .rol(entidad.getRol().getNombre())
                .build();
    }

    /**
     * Convierte una lista de entidades Usuario en una lista de DTOs de respuesta.
     */
    public static List<UsuarioResponseDTO> toDTOList(List<Usuario> entidades) {
        return entidades.stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }
}
