package org.ncapas.canchitas.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "calificacion",
        /*Restricción para que el usuario pueda
        calificar una vez una cancha y así con cada una
         */
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_cancha"}))
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion")
    private Integer idCalificacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_cancha", nullable = false)
    private Cancha cancha;

    @Column(name = "puntaje", nullable = false)
    private Integer puntaje;
}

