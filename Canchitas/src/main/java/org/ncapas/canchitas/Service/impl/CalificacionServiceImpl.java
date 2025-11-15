package org.ncapas.canchitas.Service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.ncapas.canchitas.Service.CalificacionService;
import org.ncapas.canchitas.repositories.CalificacionRepository;
import org.ncapas.canchitas.repositories.UsuarioRepostitory;
import org.ncapas.canchitas.repositories.CanchaRepository;
import org.ncapas.canchitas.entities.Calificacion;
import org.ncapas.canchitas.entities.Usuario;
import org.ncapas.canchitas.entities.Cancha;
import org.ncapas.canchitas.DTOs.request.CalificacionRequestDTO;
import org.ncapas.canchitas.DTOs.response.CalificacionResponseDTO;
import org.ncapas.canchitas.utils.mappers.CalificacionMapper;
import org.ncapas.canchitas.exception.CalificacionExist;

@Service
@RequiredArgsConstructor
public class CalificacionServiceImpl implements CalificacionService {

    private final CalificacionRepository repo;
    private final UsuarioRepository usuarioRepo;
    private final CanchaRepository canchaRepo;
    private final CalificacionMapper mapper;

    @Override
    public CalificacionResponse crearCalificacion(CalificacionRequest request) {

        if (repo.existsByUsuario_IdAndCancha_Id(request.getIdUsuario(), request.getIdCancha())) {
            throw new CalificacionYaExisteException();
        }

        Usuario usuario = usuarioRepo.findById(request.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        Cancha cancha = canchaRepo.findById(request.getIdCancha())
                .orElseThrow(() -> new RuntimeException("Cancha no existe"));

        Calificacion cal = Calificacion.builder()
                .usuario(usuario)
                .cancha(cancha)
                .puntaje(request.getPuntaje())
                .build();

        repo.save(cal);

        return mapper.toResponse(cal);
    }

    @Override
    public Double obtenerPromedioDeCancha(Integer idCancha) {

        // valida que la cancha exista
        canchaRepo.findById(idCancha)
                .orElseThrow(() -> new RuntimeException("La cancha no existe"));

        Double promedio = repo.obtenerPromedioPorCancha(idCancha);

        return promedio != null ? promedio : 0.0;
    }
}
