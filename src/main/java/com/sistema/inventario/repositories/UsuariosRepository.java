package com.sistema.inventario.repositories;

import com.sistema.inventario.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    List<Usuarios> findAll();
}
