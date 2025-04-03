package com.sistema.inventario.services;

import com.sistema.inventario.entities.Productos;
import com.sistema.inventario.repositories.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosService {
    @Autowired
    private ProductosRepository productosRepository;

    public List<Productos> listarProductos() {
        return productosRepository.findAll();
    }

    public void guardarProducto(Productos producto) {
        productosRepository.save(producto);
    }

    public Productos obtenerProductoPorId(Long idprodutcto) {
        return productosRepository.findById(idprodutcto).orElse(null);
    }

    public List<Productos> obtenerProductosActivos() {
        return productosRepository.findByEstatus(1);
    }

}
