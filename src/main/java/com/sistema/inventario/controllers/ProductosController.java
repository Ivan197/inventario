package com.sistema.inventario.controllers;

import com.sistema.inventario.entities.Historico;
import com.sistema.inventario.entities.Login;
import com.sistema.inventario.entities.Productos;
import com.sistema.inventario.repositories.HistoricoRepository;
import com.sistema.inventario.services.ProductosService;
import com.sistema.inventario.services.UsuariosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller

public class ProductosController {
    @Autowired
    private ProductosService productosService;

    @Autowired
    private UsuariosService usuarioService;

    @GetMapping("/productos")
    public String listarProductos(Model model, HttpSession session) {
        Login usuario = (Login) session.getAttribute("user");
        Long idUsuario = usuario.getIdusuario();

        List<Productos> listarProductos = productosService.listarProductos();
        model.addAttribute("productos", listarProductos);
        model.addAttribute("idrol", idUsuario);
        return "productos";
    }

    // Mostrar nuevo producto
    @GetMapping("/productos/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Productos());
        return "nuevo_producto";
    }

    // Guardar producto
    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Productos producto) {
        producto.setCantidad(0);
        producto.setEstatus(1);
        productosService.guardarProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{idproducto}")
    public String editarProducto(@PathVariable Long idproducto, String nombre, Integer cantidadAgregar, Model model) {
        Productos producto = productosService.obtenerProductoPorId(idproducto);

        if (producto == null) {
            return "redirect:/productos";
        }

        model.addAttribute("producto", producto);
        return "editar_producto";
    }

    @PostMapping("/productos/actualizar")
    public String actualizarProducto(@RequestParam Long idproducto,
                                     @RequestParam Integer cantidadAgregar,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {

        Productos producto = productosService.obtenerProductoPorId(idproducto);

        if (producto != null) {
            int cantidadActual = producto.getCantidad();


            if (cantidadAgregar <= 0) {
                redirectAttributes.addFlashAttribute("error",
                        "No puedes reducir la cantidad actual (" + cantidadActual + ").");
                return "redirect:/productos/editar/" + idproducto;
            } else {
                int nuevaCantidad = cantidadActual + cantidadAgregar;
                producto.setCantidad(nuevaCantidad);
                productosService.guardarProducto(producto);

                Login usuario = (Login) session.getAttribute("user");
                if (usuario == null) {
                    redirectAttributes.addFlashAttribute("error", "Debe iniciar sesión para realizar esta acción.");
                    return "redirect:/login";
                }

                Long idUsuario = usuario.getIdusuario();
                System.out.println("ID del usuario en sesión: " + idUsuario);

                Historico historico = new Historico();
                historico.setIdproducto(idproducto);
                historico.setMovimiento("ENTRADA");
                historico.setIdusuario(idUsuario);
                historico.setFecha(new Date());
                historicoRepository.save(historico);
            }
        }

        return "redirect:/productos";
    }

    @PostMapping("/productos/dar_baja/{idproducto}")
    public String darDeBaja(@PathVariable Long idproducto, RedirectAttributes redirectAttributes) {
        Productos producto = productosService.obtenerProductoPorId(idproducto);

        if (producto != null) {
            producto.setEstatus(0);
            productosService.guardarProducto(producto);
            redirectAttributes.addFlashAttribute("mensaje", "Producto dado de baja correctamente.");
        }

        return "redirect:/productos";
    }

    @PostMapping("/productos/activar/{idproducto}")
    public String activarProducto(@PathVariable Long idproducto, RedirectAttributes redirectAttributes) {
        Productos producto = productosService.obtenerProductoPorId(idproducto);

        if (producto != null) {
            producto.setEstatus(1);
            productosService.guardarProducto(producto);
            redirectAttributes.addFlashAttribute("mensaje", "Producto activado correctamente.");
        }

        return "redirect:/productos";
    }

    @GetMapping("/productos/salida")
    public String mostrarProductosActivos(Model model, HttpSession session) {
        Login usuario = (Login) session.getAttribute("user");
        Long idUsuario = usuario.getIdusuario();

        List<Productos> productosActivos = productosService.obtenerProductosActivos();
        model.addAttribute("productos", productosActivos);
        model.addAttribute("idrol", idUsuario);
        return "salida_productos";
    }

    @Autowired
    private HistoricoRepository historicoRepository;

    @PostMapping("/productos/salidas")
    public String actualizarCantidad(@RequestParam Long idproducto,
                                     @RequestParam String nombre,
                                     HttpSession session,
                                     @RequestParam Integer cantidadSalida,
                                     RedirectAttributes redirectAttributes) {
        Productos producto = productosService.obtenerProductoPorId(idproducto);

        if (producto != null) {
            int cantidadActual = producto.getCantidad();

            if (cantidadSalida > cantidadActual) {
                redirectAttributes.addFlashAttribute("error",
                        "No puedes restar más de la cantidad actual (" + cantidadActual + ") al producto (" + nombre + ").");
                return "redirect:/productos/salida";
            }

            producto.setCantidad(cantidadActual - cantidadSalida);
            productosService.guardarProducto(producto);
            redirectAttributes.addFlashAttribute("success", "Cantidad actualizada correctamente al producto (" + nombre + ").");


            Login usuario = (Login) session.getAttribute("user");
            if (usuario == null) {
                redirectAttributes.addFlashAttribute("error", "Debe iniciar sesión para realizar esta acción.");
                return "redirect:/login";
            }

            Long idUsuario = usuario.getIdusuario();
            System.out.println("ID del usuario en sesión: " + idUsuario);

            Historico historico = new Historico();
            historico.setIdproducto(idproducto);
            historico.setMovimiento("SALIDA");
            historico.setIdusuario(idUsuario);
            historico.setFecha(new Date());
            historicoRepository.save(historico);

        }

        return "redirect:/productos/salida";
    }
}
