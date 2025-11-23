package org.ncapas.canchitas.security.config;

import lombok.RequiredArgsConstructor;
import org.ncapas.canchitas.security.JwtFilter;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        /* ---------- Rutas públicas ---------- */
                        .requestMatchers("/api/auth/**").permitAll()                         // login
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()       // registro
                        .requestMatchers(HttpMethod.GET, "/api/lugares/zonas").permitAll()   // combo de zonas
                        .requestMatchers(HttpMethod.GET, "/api/canchas/tipos").permitAll()   // tipos de cancha
                        .requestMatchers(HttpMethod.GET, "/api/canchas/{id}").permitAll()    // detalle cancha pública

                        /* ---------- ADMIN / CLIENTE / PROPIETARIO ---------- */
                        // zonas → lugares
                        .requestMatchers(HttpMethod.GET, "/api/zonas/*/lugares")
                        .hasAnyRole("ADMIN", "CLIENTE", "PROPIETARIO")

                        // lugares → canchas
                        .requestMatchers(HttpMethod.GET, "/api/lugares/*/canchas")
                        .hasAnyRole("ADMIN", "CLIENTE", "PROPIETARIO")

                        // reservas
                        .requestMatchers(HttpMethod.POST, "/api/reservas")
                        .hasRole("CLIENTE")   // crear reserva = cliente
                        .requestMatchers(HttpMethod.GET, "/api/reservas/usuario/{id}")
                        .hasAnyRole("ADMIN", "CLIENTE", "PROPIETARIO")
                        .requestMatchers(HttpMethod.DELETE, "/api/reservas/{id}")
                        .hasAnyRole("ADMIN", "CLIENTE", "PROPIETARIO")
                        .requestMatchers(HttpMethod.DELETE, "/api/reservas")
                        .hasRole("ADMIN")     // si tienes algún delete masivo

                        /* ---------- LUGARES / CANCHAS ---------- */
                        // ADMIN ya no puede crear ni eliminar lugares/canchas
                        .requestMatchers(HttpMethod.GET, "/api/lugares/**").hasAnyRole("ADMIN", "PROPIETARIO")
                        .requestMatchers(HttpMethod.GET, "/api/canchas/**").hasAnyRole("ADMIN", "PROPIETARIO")
                        // 🔔 endpoint para la notificación: lo puede usar CLIENTE/PROPIETARIO/ADMIN
                        .requestMatchers(HttpMethod.GET, "/api/solicitudes/estado-usuario/**")
                        .hasAnyRole("CLIENTE", "PROPIETARIO", "ADMIN")

                        // Crear lugar: solo PROPIETARIO
                        .requestMatchers(HttpMethod.POST, "/api/lugares")
                        .hasRole("PROPIETARIO")
                        // Eliminar lugar: solo PROPIETARIO y admin
                        .requestMatchers(HttpMethod.DELETE, "/api/lugares/**")
                        .hasAnyRole("PROPIETARIO", "ADMIN")

                        // Crear cancha: solo PROPIETARIO
                        .requestMatchers(HttpMethod.POST, "/api/canchas")
                        .hasRole("PROPIETARIO")
                        // Eliminar cancha: solo PROPIETARIO
                        .requestMatchers(HttpMethod.DELETE, "/api/canchas/**")
                        .hasAnyRole("PROPIETARIO", "ADMIN")

                        /* ---------- Lo demás: autenticado ---------- */
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }
}

