package com.proy.backend_donaciones.web.config;

import com.proy.backend_donaciones.persistence.crud.RolCrudRepository;
import com.proy.backend_donaciones.persistence.crud.UsuarioCrudRepository;
import com.proy.backend_donaciones.persistence.entity.Rol;
import com.proy.backend_donaciones.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder; // <-- ¡IMPORTANTE! Importar
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private RolCrudRepository rolCrudRepository;
    @Autowired private UsuarioCrudRepository usuarioCrudRepository; // <-- Añadir
    @Autowired private PasswordEncoder passwordEncoder; // <-- Añadir

    @Override
    public void run(String... args) throws Exception {
        // Primero creamos los roles
        createRoleIfNotFound("DONANTE_PERSONA");
        createRoleIfNotFound("DONANTE_RESTAURANTE");
        createRoleIfNotFound("ADMIN");

        // Luego, creamos el usuario administrador
        createAdminUserIfNotFound();
    }

    private void createRoleIfNotFound(String roleName) {
        if (rolCrudRepository.findByNombre(roleName).isEmpty()) {
            Rol newRol = new Rol();
            newRol.setNombre(roleName);
            rolCrudRepository.save(newRol);
            System.out.println("Creado rol: " + roleName);
        }
    }

    // --- NUEVO MÉTODO PARA CREAR EL ADMIN ---
    private void createAdminUserIfNotFound() {
        // 1. Definimos los datos del admin
        String adminEmail = "admin@donaciones.com";

        // 2. Verificamos si el admin ya existe para no crearlo de nuevo
        if (usuarioCrudRepository.findByEmail(adminEmail).isEmpty()) {

            // 3. Buscamos el rol de ADMIN, que ya debería existir
            Rol adminRole = rolCrudRepository.findByNombre("ADMIN")
                    .orElseThrow(() -> new RuntimeException("Error: El rol ADMIN no se encontró en la base de datos."));

            // 4. Creamos la nueva entidad de Usuario
            Usuario adminUser = new Usuario();
            adminUser.setNombre("Admin");
            adminUser.setApellido("Principal");
            adminUser.setEmail(adminEmail);
            // ¡MUY IMPORTANTE! Siempre guardar la contraseña codificada
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setDni("00000000"); // DNI de relleno
            adminUser.setRoles(Set.of(adminRole)); // Asignamos el rol

            // 5. Guardamos el nuevo usuario admin en la base de datos
            usuarioCrudRepository.save(adminUser);
            System.out.println("Creado usuario administrador: " + adminEmail);
        }
    }
}