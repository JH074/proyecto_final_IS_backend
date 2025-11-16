package org.ncapas.canchitas.repositories;

import org.ncapas.canchitas.entities.SolicitudPropietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudPropietarioRepository extends JpaRepository<SolicitudPropietario, Integer> {
    List<SolicitudPropietario> findByEstadoSolicitud(SolicitudPropietario.EstadoSolicitud estado);
    boolean existsByUsuario_IdUsuario(Integer idUsuario);
}
