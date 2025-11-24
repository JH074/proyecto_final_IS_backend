package org.ncapas.canchitas.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "solicitud_propietarios")
public class SolicitudPropietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @NotBlank
    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @NotBlank
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @NotBlank
    @Pattern(regexp = "^[0-9]{8}-[0-9]{1}$", message = "El DUI debe tener el formato ########-#")
    @Column(name = "dui", nullable = false)
    private String dui;

    @NotBlank
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}$", message = "El teléfono debe tener el formato ####-####")
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @NotBlank
    @Email(message = "El correo no tiene un formato válido")
    @Column(name = "correo", nullable = false)
    private String correo;

    // Información del lugar
    @NotBlank
    @Column(name = "nombre_lugar", nullable = false)
    private String nombreLugar;

    @NotBlank
    @Column(name = "direccion_lugar", nullable = false)
    private String direccionLugar;

    @NotBlank
    @Pattern(regexp = "^[0-9]{4}-[0-9]{6}-[0-9]{3}-[0-9]{1}$", message = "El NIT debe tener el formato ####-######-###-#")
    @Column(name = "nit", nullable = false)
    private String nit;

    @NotBlank
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}$", message = "El teléfono debe tener el formato ####-####")
    @Column(name = "telefono_lugar", nullable = false)
    private String telefonoLugar;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_solicitud", nullable = false)
    private Date fechaSolicitud;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_solicitud", nullable = false)
    private EstadoSolicitud estadoSolicitud;

    @Column(name = "motivo_rechazo", length = 1000)
    private String motivoRechazo;


    public enum EstadoSolicitud {
        PENDIENTE,
        APROBADA,
        RECHAZADA;

        public static EstadoSolicitud from(String value) {
            return EstadoSolicitud.valueOf(value.trim().toUpperCase());
        }

        @Override
        public String toString() {
            return this.name();
        }
    }

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_zona", nullable = false)
    private Zona zona;


}

