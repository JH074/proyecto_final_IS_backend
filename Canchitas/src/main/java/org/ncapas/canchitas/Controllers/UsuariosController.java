package org.ncapas.canchitas.Controllers;

import org.ncapas.canchitas.DTOs.request.UsuarioRequestDTO;
import org.ncapas.canchitas.DTOs.response.UsuarioResponseDTO;
import org.ncapas.canchitas.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /** GET /api/v1/usuarios — Listar todos los usuarios */
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> lista = usuarioService.findAll();
        return ResponseEntity.ok(lista);
    }

    /** GET /api/v1/usuarios/{id} — Obtener un usuario por su ID */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuario(@PathVariable int id) {
        UsuarioResponseDTO dto = usuarioService.findById(id);
        return ResponseEntity.ok(dto);
    }

    /** POST /api/v1/usuarios — Crear un nuevo usuario */
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(
            @Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO creada = usuarioService.save(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creada);
    }

    /** DELETE /api/v1/usuarios/{id} — Eliminar un usuario */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
