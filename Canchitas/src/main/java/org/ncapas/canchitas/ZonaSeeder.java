package org.ncapas.canchitas;


import lombok.RequiredArgsConstructor;
import org.ncapas.canchitas.entities.Zona;
import org.ncapas.canchitas.repositories.ZonaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZonaSeeder implements CommandLineRunner {

    private final ZonaRepository zonaRepo;

    @Override public void run(String... args) {
        if (zonaRepo.count() == 0) {
            zonaRepo.save(Zona.builder().departamento("San Salvador").distrito("Centro").build());
            zonaRepo.save(Zona.builder().departamento("La Libertad").distrito("Santa Tecla").build());
            zonaRepo.save(Zona.builder().departamento("San Miguel").distrito("Ciudad").build());
            System.out.println("Zonas precargadas ✓");
        }
    }
}
