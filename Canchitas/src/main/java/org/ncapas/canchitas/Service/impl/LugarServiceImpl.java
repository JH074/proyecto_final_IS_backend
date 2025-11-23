package org.ncapas.canchitas.Service.impl;

import lombok.RequiredArgsConstructor;
import org.ncapas.canchitas.DTOs.request.LugarRequestDTO;
import org.ncapas.canchitas.DTOs.response.LugarResponseDTO;
import org.ncapas.canchitas.entities.Lugar;
import org.ncapas.canchitas.entities.Usuario;
import org.ncapas.canchitas.entities.Zona;
import org.ncapas.canchitas.exception.LugarNotFoundException;
import org.ncapas.canchitas.repositories.LugarRepository;
import org.ncapas.canchitas.repositories.UsuarioRepostitory;
import org.ncapas.canchitas.repositories.ZonaRepository;
import org.ncapas.canchitas.Service.LugarService;
import org.ncapas.canchitas.utils.mappers.LugarMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LugarServiceImpl implements LugarService {

    private final LugarRepository lugarRepo;
    private final ZonaRepository zonaRepo;
    private final UsuarioRepostitory usuarioRepo;


    @Override
    public List<LugarResponseDTO> findAll() {
        return LugarMapper.toDTOList(lugarRepo.findAll());
    }

    @Override
    public LugarResponseDTO findById(int id) {
        Lugar lugar = lugarRepo.findById(id)
                .orElseThrow(() -> new LugarNotFoundException("Lugar no encontrado con id " + id));
        return LugarMapper.toDTO(lugar);
    }

    @Override
    public LugarResponseDTO save(LugarRequestDTO dto) {

        // 1) Validar campos
        validarCamposLlenos(dto);

        // 2) Cargar la zona relacionada
        Zona zona = zonaRepo.findById(dto.getZona())
                .orElseThrow(() -> new LugarNotFoundException("Zona no encontrada con id " + dto.getZona()));

        // 3) Obtener usuario logueado desde el JWT
        String correo = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();  // normalmente es el email/username

        Usuario propietario = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() ->
                        new RuntimeException("No se encontró el propietario con correo " + correo));

        // 4) Mapear DTO → entidad con propietario
        Lugar nueva = Lugar.builder()
                .nombre(dto.getNombre())
                .direccion(dto.getDireccion())
                .codigo(dto.getCodigo())
                .zona(zona)
                .propietario(propietario)
                .build();

        // 5) Guardar y devolver DTO
        Lugar guardada = lugarRepo.save(nueva);
        return LugarMapper.toDTO(guardada);
    }

    @Override
    public void delete(int id) {
        if (!lugarRepo.existsById(id)) {
            throw new LugarNotFoundException("No existe lugar con id " + id);
        }
        lugarRepo.deleteById(id);
    }

    //validar campos
    private void validarCamposLlenos(LugarRequestDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank()
                || dto.getDireccion() == null || dto.getDireccion().isBlank()
                || dto.getCodigo() == null
                || dto.getZona() == null) {
            throw new IllegalArgumentException("Todos los campos del formulario de lugar deben estar completos.");
        }

    }

    @Override
    public List<LugarResponseDTO> findByPropietario(Integer idPropietario) {
        // Busca los lugares cuyo propietario tenga ese idUsuario
        var lugares = lugarRepo.findByPropietario_IdUsuario(idPropietario);
        return LugarMapper.toDTOList(lugares);
    }
}
