package com.sistema.inventario.controllers;

import com.sistema.inventario.entities.Historico;
import com.sistema.inventario.repositories.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HistoricoController {
    @Autowired
    private HistoricoRepository historicoRepository;

    @GetMapping("/historico")
    public String listarHistorico(@RequestParam(value = "movimiento", required = false) String movimiento,
                                  Model model) {
        List<Historico> historicos;

        if (movimiento != null && !movimiento.isEmpty()) {
            historicos = historicoRepository.findByMovimiento(movimiento);
        } else {
            historicos = historicoRepository.findAll();
        }

        model.addAttribute("historicos", historicos);
        model.addAttribute("movimiento", movimiento);
        return "historico";
    }

}
