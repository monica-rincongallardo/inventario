package com.prueba.inventario.controller;


import com.prueba.inventario.entity.HistoricoMovimiento;
import com.prueba.inventario.entity.Producto;
import com.prueba.inventario.service.HistoricoMovimientoService;
import com.prueba.inventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// Controller para manejar las vistas del inventario
@Controller
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private HistoricoMovimientoService historicoMovimientoService;

    // mostrar listado de productos
    @GetMapping("/inventario")
    public String mostrarInventario(Model model) {
        List<Producto> listaProductos = productoService.obtenerTodos();
        model.addAttribute("productos", listaProductos);
        return "inventario";
    }


    // mostrar formulario (agregar producto)
    @GetMapping("/nuevo-producto")
    public String mostrarFormulario(Model model) {
        Producto producto = new Producto();
        model.addAttribute("producto", producto);
        return "nuevo-producto";
    }

    // guardar producto
    @PostMapping("guardar-producto")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/inventario";
    }


    // mostrar formulario de entrada
    @GetMapping("/entrada-producto/{id}")
    public String mostrarFormularioEntrada(@PathVariable Integer id, Model model) {
        Producto producto = productoService.buscarPorId(id);
        model.addAttribute("producto", producto);
        return "entrada-producto";
    }

    // guardar entrada
    @PostMapping("/guardar-entrada")
    public String guardarEntrada(
            @RequestParam Integer idProducto, @RequestParam Integer cantidad, RedirectAttributes redirectAttributes) {

        try {
            productoService.aumentarInventario(idProducto, cantidad);
            redirectAttributes.addFlashAttribute("success", "Entrada registrada correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/inventario";
    }


    // cambiar estatus del producto
    @GetMapping("/cambiar-estatus/{id}")
    public String cambiarEstatus(@PathVariable Integer id) {
        productoService.cambiarEstatus(id);
        return "redirect:/inventario";
    }


    // mostrar formulario de salida
    @GetMapping("/salida-producto/{id}")
    public String mostrarFormularioSalida(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Producto producto = productoService.buscarPorId(id);

        // validar si está inactivo
        if (producto.getEstatus() == 0) {
            redirectAttributes.addFlashAttribute("error", "No se pueden realizar salidas de productos inactivos");
            return "redirect:/inventario";
        }
        model.addAttribute("producto", producto);
        return "salida-producto";
    }

    // guardar salida
    @PostMapping("/guardar-salida")
    public String guardarSalida(
            @RequestParam Integer idProducto, @RequestParam Integer cantidad, RedirectAttributes redirectAttributes) {

        try {
            productoService.disminuirInventario(idProducto, cantidad);
            redirectAttributes.addFlashAttribute("success", "Salida registrada correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/inventario";
    }


    // mostar listado de historial
    @GetMapping("/historial")
    public String mostrarHistorial(@RequestParam (required = false) String tipo, Model model) {
        List<HistoricoMovimiento> movimientos;

        // si selecciona filtro
        if (tipo != null && !tipo.isEmpty()) {
            movimientos = historicoMovimientoService.filtrarPorTipo(tipo);
        }
        else {
            // mostrar todos
            movimientos = historicoMovimientoService.listarMovimientos();
        }

        model.addAttribute("movimientos", movimientos);
        return "historial";
    }
}
