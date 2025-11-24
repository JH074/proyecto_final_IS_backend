package org.ncapas.canchitas.Controllers;

import lombok.RequiredArgsConstructor;
import org.ncapas.canchitas.entities.Rol;
import org.ncapas.canchitas.repositories.RolRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolRepository rolRepository;

    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles() {
        return ResponseEntity.ok(rolRepository.findAll());
    }
}
