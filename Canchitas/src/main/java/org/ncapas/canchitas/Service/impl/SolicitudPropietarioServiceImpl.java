    package org.ncapas.canchitas.Service.impl;
    
    import lombok.RequiredArgsConstructor;
    import org.ncapas.canchitas.DTOs.request.SolicitudPropietarioRequestDTO;
    import org.ncapas.canchitas.DTOs.response.EstadoSolicitudUsuarioDTO;
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
    import org.ncapas.canchitas.repositories.RolRepository;
    import org.ncapas.canchitas.Service.SolicitudPropietarioService;
    import org.springframework.stereotype.Service;
    
    import java.util.List;
    
    @Service
    @RequiredArgsConstructor
    public class SolicitudPropietarioServiceImpl implements SolicitudPropietarioService {
    
        private final SolicitudPropietarioRepository solicitudRepository;
        private final UsuarioRepostitory usuarioRepository;
        private final ZonaRepository zonaRepository;
        private final RolRepository rolRepository;
    
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
    
    
        @Override
        public SolicitudPropietarioResponseDTO obtenerPorId(Integer idSolicitud) {
            SolicitudPropietario solicitud = solicitudRepository.findById(idSolicitud)
                    .orElseThrow(() -> new SolicitudNotFoundException(idSolicitud));
            return SolicitudPropietarioMapper.toDTO(solicitud);
        }


        @Override
        public void rechazarSolicitud(Integer idSolicitud, String motivo) {
            // 1) Buscar la solicitud
            SolicitudPropietario solicitud = solicitudRepository.findById(idSolicitud)
                    .orElseThrow(() -> new SolicitudNotFoundException(idSolicitud));

            // 2) Cambiar estado a RECHAZADA
            solicitud.setEstadoSolicitud(SolicitudPropietario.EstadoSolicitud.RECHAZADA);
            solicitud.setMotivoRechazo(motivo);
            // 3) Guardar cambios
            solicitudRepository.save(solicitud);
        }



        @Override
        public void aprobarSolicitud(Integer idSolicitud) {
    
            // 1) Buscar la solicitud
            SolicitudPropietario solicitud = solicitudRepository.findById(idSolicitud)
                    .orElseThrow(() -> new SolicitudNotFoundException(idSolicitud));
    
            // 2) Obtener el usuario que hizo la solicitud
            Usuario usuario = solicitud.getUsuario();
            if (usuario == null) {
                throw new UsuarioNotFoundException("La solicitud no tiene un usuario asociado");
            }
    
            // 3) Buscar el rol PROPIETARIO
            Rol rolPropietario = rolRepository.findByNombreIgnoreCase("PROPIETARIO")
                    .orElseThrow(() -> new RuntimeException("El rol PROPIETARIO no existe en la base de datos"));
    
            // 4) Asignar el rol PROPIETARIO al usuario
            usuario.setRol(rolPropietario);
            usuarioRepository.save(usuario);
    
    
            // 5) Opcional: marcar la solicitud como aprobada si tienes un campo estado
            solicitud.setEstadoSolicitud(SolicitudPropietario.EstadoSolicitud.APROBADA);
            solicitudRepository.save(solicitud);
        }


        // SolicitudPropietarioServiceImpl.java

        @Override
        public EstadoSolicitudUsuarioDTO obtenerEstadoPorUsuario(Integer idUsuario) {

            return solicitudRepository
                    .findTopByUsuario_IdUsuarioOrderByFechaSolicitudDesc(idUsuario)
                    .map(s -> EstadoSolicitudUsuarioDTO.builder()
                            .idSolicitud(s.getIdSolicitud())
                            .estadoSolicitud(s.getEstadoSolicitud().name())  // PENDIENTE / APROBADA / RECHAZADA
                            .motivoRechazo(s.getMotivoRechazo())          // <-- NUEVO
                            .build()
                    )
                    // Si el usuario aún no tiene solicitudes, devolvemos un estado neutro
                    .orElseGet(() -> EstadoSolicitudUsuarioDTO.builder()
                            .idSolicitud(null)
                            .estadoSolicitud("SIN_SOLICITUD")
                            .build()
                    );
        }



    }
    
    
