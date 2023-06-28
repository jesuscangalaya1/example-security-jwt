package com.crud.util;

import com.crud.security.entity.Rol;
import com.crud.security.enums.RolNombre;
import com.crud.security.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * MUY IMPORTANTE: ESTA CLASE SÓLO SE EJECUTARÁ UNA VEZ PARA CREAR LOS ROLES.
 * UNA VEZ CREADOS SE DEBERÁ ELIMINAR O BIEN COMENTAR EL CÓDIGO
 *
 */

@Component
@RequiredArgsConstructor
public class CreateRoles implements CommandLineRunner {

    private final RolService rolService;

   @Override
   public void run(String... args) {
       /*
         Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
         Rol rolUser = new Rol(RolNombre.ROLE_USER);
         rolService.save(rolAdmin);
         rolService.save(rolUser);

    */
   }
}
