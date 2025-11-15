package org.ncapas.canchitas.repositories;

import org.ncapas.canchitas.entities.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {

    boolean existsByUsuario_IdAndCancha_Id(Integer idUsuario, Integer idCancha);

    @Query("SELECT AVG(c.puntaje) FROM Calificacion c WHERE c.cancha.id = :idCancha")
    Double obtenerPromedioPorCancha(Integer idCancha);
}
