package org.ncapas.canchitas.Controllers;

import lombok.RequiredArgsConstructor;
import org.ncapas.canchitas.DTOs.request.AuthRequestDTO;
import org.ncapas.canchitas.DTOs.response.AuthResponseDTO;
import org.ncapas.canchitas.repositories.UsuarioRepostitory;
import org.ncapas.canchitas.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepostitory usuarioRepo;        // ← inyecta aquí tu repo
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO req) {
        // 1) Autenticar
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getCorreo(), req.getContrasena()
                )
        );

        // 2) Generar token
        String token = jwtUtil.generate(req.getCorreo());

        // 3) Extraer el rol y quitar el prefijo "ROLE_"
        String authority = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst().orElse("CLIENTE");
        String role = authority.replaceFirst("^ROLE_", "");

        var usuario = usuarioRepo.findByCorreo(req.getCorreo())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // 4) Devolver token + rol
        AuthResponseDTO dto = new AuthResponseDTO(
                token,
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                role
        );

        return ResponseEntity.ok(dto);
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {

        return ResponseEntity.ok(
                Map.of("mensaje", "La sesión ha sido cerrada")
        );
    }
}