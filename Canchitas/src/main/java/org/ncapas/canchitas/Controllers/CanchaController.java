package org.ncapas.canchitas.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ncapas.canchitas.DTOs.request.*;
import org.ncapas.canchitas.DTOs.response.*;
import org.ncapas.canchitas.Service.CanchasService;
import org.ncapas.canchitas.Service.ReservaService;
import org.ncapas.canchitas.entities.Semana;
import org.ncapas.canchitas.entities.TipoCancha;
import org.ncapas.canchitas.repositories.TipoCanchaRepository;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/canchas")
@RequiredArgsConstructor
public class CanchaController {

    private final CanchasService canchasService;
    private final TipoCanchaRepository tipoRepo;
    private final ReservaService reservaService;

    /* ------------------------------------------------------------------
     * 1) Combo de tipos de cancha (público) ─ GET /api/canchas/tipos
     * ------------------------------------------------------------------ */
    @GetMapping("/tipos")
    public List<TipoCanchaComboDTO> listarTipos() {
        return tipoRepo.findAll().stream()
                .map(this::mapTipo)
                .toList();
    }

    /* ------------------------------------------------------------------
     * 2) Crear cancha (solo PROPIETARIO) ─ POST /api/canchas
     * ------------------------------------------------------------------ */
    @PreAuthorize("hasRole('PROPIETARIO')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CanchasResponseDTO> crearCancha(
            @Valid @RequestBody CanchaRequestDTO dto) {

        CanchasResponseDTO creada = canchasService.save(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creada.getIdCancha())
                .toUri();

        return ResponseEntity.created(location).body(creada);
    }

    /* ------------------------------------------------------------------
     * 3) Listar todas (cualquier rol autenticado) ─ GET /api/canchas
     * ------------------------------------------------------------------ */
    @GetMapping
    public List<CanchasResponseDTO> listarCanchas() {
        return canchasService.findAll();
    }

    /* ------------------------------------------------------------------
     * 4) Detalle por ID ─ GET /api/canchas/{id}
     * ------------------------------------------------------------------ */
    @GetMapping("/{id}")
    public ResponseEntity<CanchasResponseDTO> detalle(@PathVariable int id) {
        return ResponseEntity.ok(canchasService.findById(id));
    }

    /* ------------------------------------------------------------------
     * 5) Actualizar (solo PROPIETARIO) ─ PUT /api/canchas/{id}
     * ------------------------------------------------------------------ */
    @PreAuthorize("hasRole('PROPIETARIO')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> actualizar(
            @PathVariable int id,
            @Valid @RequestBody CanchaUpdateRequestDTO dto) {

        dto.setIdCancha(id);
        CanchasResponseDTO updated = canchasService.update(dto);

        Map<String, Object> body = Map.of(
                "mensaje", "La cancha \"" + updated.getNombre()
                        + "\" (id=" + updated.getIdCancha() + ") ha sido actualizada",
                "cancha", updated
        );
        return ResponseEntity.ok(body);
    }

    /* ------------------------------------------------------------------
     * 6) Eliminar (solo PROPIETARIO) ─ DELETE /api/canchas/{id}
     * ------------------------------------------------------------------ */
    @PreAuthorize("hasAnyRole('PROPIETARIO','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable int id) {

        CanchasResponseDTO cancha = canchasService.findById(id);
        canchasService.delete(id);

        String msg = "La cancha \"" + cancha.getNombre()
                + "\" (id=" + cancha.getIdCancha() + ") ha sido eliminada";

        Map<String, String> body = Map.of("mensaje", msg);
        return ResponseEntity.ok(body);
    }

    /* ------------------------------------------------------------------
     * 7) Traer horarios por cancha ─ GET /api/canchas/{id}/jornadas
     * ------------------------------------------------------------------ */
    @GetMapping("/{id}/jornadas")
    public ResponseEntity<List<JornadaResponseDTO>> jornadasPorDia(
            @PathVariable int id,
            @RequestParam String dia) {

        Semana.Dia diaEnum;
        try {
            diaEnum = Semana.Dia.from(dia);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }

        var lista = canchasService.findJornadasByCanchaAndDia(id, diaEnum);
        return ResponseEntity.ok(lista);
    }

    /* ------------------------------------------------------------------
     * 8) OBTENER RESERVAS POR CANCHA (ADMIN o PROPIETARIO)
     * ------------------------------------------------------------------ */
    @PreAuthorize("hasAnyRole('ADMIN','PROPIETARIO')")
    @GetMapping("/{id}/reservas")
    public ResponseEntity<List<ReservaResponseDTO>> reservasPorCancha(
            @PathVariable int id) {

        // lanza 404 si la cancha no existe
        canchasService.findById(id);

        List<ReservaResponseDTO> lista = reservaService.findByCanchaId(id);
        return ResponseEntity.ok(lista);
    }

    /* ------------------------------------------------------------------
     * AUXILIAR: convertir TipoCancha a combo DTO
     * ------------------------------------------------------------------ */
    private TipoCanchaComboDTO mapTipo(TipoCancha t) {
        return new TipoCanchaComboDTO(
                t.getIdTipoCancha(),
                t.getTipo().name()
        );
    }

    @PreAuthorize("hasAnyRole('PROPIETARIO','ADMIN')")
    @GetMapping("/mis-canchas/{idPropietario}")
    public ResponseEntity<List<CanchasResponseDTO>> misCanchas(
            @PathVariable Integer idPropietario) {

        List<CanchasResponseDTO> lista = canchasService.findByPropietario(idPropietario);
        return ResponseEntity.ok(lista);
    }
}


