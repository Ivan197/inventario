package com.sistema.inventario.repositories;

import com.sistema.inventario.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByNombreAndContrasena(String nombre, String contrasena);
}