package org.ncapas.canchitas.Service.impl;

import lombok.RequiredArgsConstructor;
import org.ncapas.canchitas.DTOs.request.SolicitudPropietarioRequestDTO;
import org.ncapas.canchitas.DTOs.response.SolicitudPropietarioResponseDTO;
import org.ncapas.canchitas.entities.Rol;
import org.ncapas.canchitas.entities.SolicitudPropietario;
import org.ncapas.canchitas.entities.Usuario;
import org.ncapas.canchitas.entities.Zona;
import org.ncapas.canchitas.exception.SolicitudNotFoundException;
import org.ncapas.canchitas.exception.UsuarioNotFoundException;
import org.ncapas.canchitas.utils.mappers.SolicitudPropietarioMapper;
import org.ncapas.canchitas.repositories.SolicitudPropietarioRepository;
import org.ncapas.canchitas.repositories.UsuarioRepostitory;
import org.ncapas.canchitas.repositories.ZonaRepository;
import org.ncapas.canchitas.Service.SolicitudPropietarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudPropietarioServiceImpl implements SolicitudPropietarioService {

    private final SolicitudPropietarioRepository solicitudRepository;
    private final UsuarioRepostitory usuarioRepository;
    private final ZonaRepository zonaRepository;

    @Override
    public SolicitudPropietarioResponseDTO crearSolicitud(SolicitudPropietarioRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new UsuarioNotFoundException("El usuario no existe"));

        Zona zona = zonaRepository.findById(dto.getIdZona())
                .orElseThrow(() -> new RuntimeException("La zona seleccionada no existe"));

        SolicitudPropietario solicitud = SolicitudPropietarioMapper.toEntity(dto, usuario);

        solicitud.setZona(zona);
        solicitudRepository.save(solicitud);

        return SolicitudPropietarioMapper.toDTO(solicitud);
    }

    @Override
    public List<SolicitudPropietarioResponseDTO> listarSolicitudes() {
        return solicitudRepository.findAll()
                .stream()
                .map(SolicitudPropietarioMapper::toDTO)
                .toList();
    }
}

