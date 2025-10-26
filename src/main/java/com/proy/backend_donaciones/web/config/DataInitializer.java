package com.proy.backend_donaciones.web.config;

import com.proy.backend_donaciones.persistence.crud.RolCrudRepository;
import com.proy.backend_donaciones.persistence.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolCrudRepository rolCrudRepository;

    @Override
    public void run(String... args) throws Exception {
        // Llama a un método para crear cada rol, solo si no existe
        createRoleIfNotFound("DONANTE_PERSONA");
        createRoleIfNotFound("DONANTE_RESTAURANTE");
        // Puedes añadir más roles aquí en el futuro
        // createRoleIfNotFound("RECEPTOR_ONG");
        // createRoleIfNotFound("ADMIN");
    }

    private void createRoleIfNotFound(String roleName) {
        Optional<Rol> rol = rolCrudRepository.findByNombre(roleName);
        if (rol.isEmpty()) {
            Rol newRol = new Rol();
            newRol.setNombre(roleName);
            rolCrudRepository.save(newRol);
            System.out.println("Creado rol: " + roleName);
        }
    }
}
