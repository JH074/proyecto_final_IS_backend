package org.ncapas.canchitas.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.ncapas.canchitas.Service.CalificacionService;
import org.ncapas.canchitas.DTOs.request.CalificacionRequestDTO;

@RestController
@RequestMapping("/api/calificaciones")
@RequiredArgsConstructor
public class CalificacionController {

    private final CalificacionService service;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CalificacionRequestDTO request) {
        return ResponseEntity.ok(service.crearCalificacion(request));
    }

    @GetMapping("/promedio/{idCancha}")
    public ResponseEntity<?> obtenerPromedio(@PathVariable Integer idCancha) {
        return ResponseEntity.ok(service.obtenerPromedioDeCancha(idCancha));
    }

    @GetMapping("/total/{idCancha}")
    public ResponseEntity<Long> obtenerTotalCalificaciones(@PathVariable Integer idCancha) {
        Long total = service.obtenerTotalCalificacionesPorCancha(idCancha);
        return ResponseEntity.ok(total);
    }
}
