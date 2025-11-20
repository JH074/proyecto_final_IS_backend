package org.ncapas.canchitas.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ncapas.canchitas.DTOs.request.SolicitudPropietarioRequestDTO;
import org.ncapas.canchitas.DTOs.response.SolicitudPropietarioResponseDTO;
import org.ncapas.canchitas.Service.SolicitudPropietarioService;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudPropietarioController {

    private final SolicitudPropietarioService solicitudService;

    /* ---------------------------------------------------------
     * 1) Crear solicitud (CLIENTE)
     * --------------------------------------------------------- */
    @PreAuthorize("hasRole('CLIENTE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SolicitudPropietarioResponseDTO> crearSolicitud(
            @Valid @RequestBody SolicitudPropietarioRequestDTO dto
    ) {
        var creada = solicitudService.crearSolicitud(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    /* ---------------------------------------------------------
     * 2) Listar solicitudes (ADMIN)
     * --------------------------------------------------------- */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<SolicitudPropietarioResponseDTO>> listarSolicitudes() {
        return ResponseEntity.ok(solicitudService.listarSolicitudes());
    }



    /* ---------------------------------------------------------
     * 3) Aprobar solicitud (ADMIN → convierte usuario en PROPIETARIO)
     * --------------------------------------------------------- */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/aprobar")
    public ResponseEntity<String> aprobarSolicitud(@PathVariable Integer id) {
        solicitudService.aprobarSolicitud(id);
        return ResponseEntity.ok("Solicitud aprobada. El usuario ahora es PROPIETARIO");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudPropietarioResponseDTO> detalleSolicitud(@PathVariable Integer id) {
        SolicitudPropietarioResponseDTO dto = solicitudService.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }

}
