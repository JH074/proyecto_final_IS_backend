package org.ncapas.canchitas.Service.impl;

import lombok.RequiredArgsConstructor;
import org.ncapas.canchitas.DTOs.request.UsuarioRequestDTO;
import org.ncapas.canchitas.DTOs.response.UsuarioResponseDTO;
import org.ncapas.canchitas.entities.Usuario;
import org.ncapas.canchitas.entities.Rol;
import org.ncapas.canchitas.exception.UsuarioNotFoundException;
import org.ncapas.canchitas.repositories.RolRepository;
import org.ncapas.canchitas.repositories.UsuarioRepostitory;
import org.ncapas.canchitas.Service.UsuarioService;
import org.ncapas.canchitas.utils.mappers.UsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepostitory usuarioRepo;
    private final RolRepository rolRepo;

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return UsuarioMapper.toDTOList(usuarioRepo.findAll());
    }

    @Override
    public UsuarioResponseDTO findById(int id) {
        Usuario u = usuarioRepo.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con id " + id));
        return UsuarioMapper.toDTO(u);
    }

    @Override
    public UsuarioResponseDTO save(UsuarioRequestDTO dto) {
        // mapea el DTO pero crea Rol “stub”
        Usuario entidad = UsuarioMapper.toEntity(dto);
        // busca rol real
        Rol rol = rolRepo.findById(dto.getRolId())
                .orElseThrow(() -> new UsuarioNotFoundException("Rol no encontrado con id " + dto.getRolId()));
        entidad.setRol(rol);
        Usuario guardado = usuarioRepo.save(entidad);
        return UsuarioMapper.toDTO(guardado);
    }

    @Override
    public void delete(int id) {
        if (!usuarioRepo.existsById(id)) {
            throw new UsuarioNotFoundException("Usuario no existe con id " + id);
        }
        usuarioRepo.deleteById(id);
    }
}
