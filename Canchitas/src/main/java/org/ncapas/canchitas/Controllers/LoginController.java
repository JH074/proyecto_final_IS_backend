package org.ncapas.canchitas.Controllers;



import lombok.RequiredArgsConstructor;
import org.ncapas.canchitas.DTOs.request.AuthRequestDTO;
import org.ncapas.canchitas.DTOs.response.AuthResponseDTO;
import org.ncapas.canchitas.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

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
                .findFirst()
                .orElse("CLIENTE");  // por defecto
        String rol = authority.startsWith("ROLE_")
                ? authority.substring("ROLE_".length())
                : authority;

        // 4) Devolver token + rol
        return ResponseEntity.ok(new AuthResponseDTO(token, rol));
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {

        return ResponseEntity.ok(
                Map.of("mensaje", "La sesión ha sido cerrada")
        );
    }
}