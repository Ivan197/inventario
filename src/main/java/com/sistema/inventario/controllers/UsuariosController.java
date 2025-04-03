package com.sistema.inventario.controllers;

import com.sistema.inventario.entities.Usuarios;
import com.sistema.inventario.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsuariosController {
    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        List<Usuarios> listaUsuarios = usuariosService.listarUsuarios();
        model.addAttribute("usuarios", listaUsuarios);
        return "usuarios";
    }
}
