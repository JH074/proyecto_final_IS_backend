package org.ncapas.canchitas.repositories;

import org.ncapas.canchitas.entities.SolicitudPropietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolicitudPropietarioRepository extends JpaRepository<SolicitudPropietario, Integer> {
    Optional<SolicitudPropietario> findTopByUsuario_IdUsuarioOrderByFechaSolicitudDesc(Integer idUsuario);

}

