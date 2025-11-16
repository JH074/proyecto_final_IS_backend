package org.ncapas.canchitas.repositories;

import org.ncapas.canchitas.entities.SolicitudPropietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudPropietarioRepository extends JpaRepository<SolicitudPropietario, Integer> {
}

