package org.ncapas.canchitas;

import lombok.RequiredArgsConstructor;
import org.ncapas.canchitas.entities.Rol;
import org.ncapas.canchitas.repositories.RolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataseederRol implements CommandLineRunner {

    private final RolRepository rolRepo;

    @Override
    public void run(String... args) {

        crearRolSiNoExiste("ADMIN");
        crearRolSiNoExiste("PROPIETARIO");
        crearRolSiNoExiste("CLIENTE");

        System.out.println("Roles verificados/creados: ADMIN, PROPIETARIO, CLIENTE");
    }

    private void crearRolSiNoExiste(String nombre) {
        rolRepo.findByNombreIgnoreCase(nombre)
                .orElseGet(() -> {
                    Rol rol = Rol.builder()
                            .nombre(nombre)
                            .build();
                    return rolRepo.save(rol);
                });
    }
}

