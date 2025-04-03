package com.sistema.inventario.services;


import com.sistema.inventario.entities.Usuarios;
import com.sistema.inventario.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService {
    @Autowired
    private UsuariosRepository usuariosRepository;

    public List<Usuarios> listarUsuarios() {
        return usuariosRepository.findAll();
    }
}
