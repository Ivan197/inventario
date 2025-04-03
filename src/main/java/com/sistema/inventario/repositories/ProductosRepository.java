package com.sistema.inventario.repositories;

import com.sistema.inventario.entities.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductosRepository extends JpaRepository<Productos, Long> {
    List<Productos> findAll();

    List<Productos> findByEstatus(int estatus);
}
