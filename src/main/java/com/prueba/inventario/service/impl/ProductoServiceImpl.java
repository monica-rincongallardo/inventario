package com.prueba.inventario.service.impl;

import com.prueba.inventario.entity.HistoricoMovimiento;
import com.prueba.inventario.entity.Producto;
import com.prueba.inventario.entity.Usuario;
import com.prueba.inventario.repository.ProductoRepository;
import com.prueba.inventario.repository.UsuarioRepository;
import com.prueba.inventario.service.HistoricoMovimientoService;
import com.prueba.inventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.time.LocalDateTime;
import java.util.List;

// Implementación de la lógica de productos
@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private HistoricoMovimientoService historicoMovimientoService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    // obtener todos los productos
    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }


    // guardar producto nuevo (cantidad inicial 0)
    @Override
    public Producto guardarProducto(Producto producto) {
        producto.setStock(0);
        producto.setEstatus(1);

        return productoRepository.save(producto);
    }


    // buscar producto por id
    @Override
    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }


    /*
    cambiar estatus:
    1 = activo
    0 = inactivo
    */
    @Override
    public void cambiarEstatus(Integer id) {
        Producto producto = buscarPorId(id);

        if (producto != null) {
            if (producto.getEstatus() == 1) {
                producto.setEstatus(0);
            } else {
                producto.setEstatus(1);
            }

            productoRepository.save(producto);
        }
    }


    // aumentar inventario
    @Override
    public void aumentarInventario(Integer idProducto, Integer cantidad) {
        if (cantidad <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }

        Producto producto = buscarPorId(idProducto);

        if (producto != null) {
            int nuevoStock = producto.getStock() + cantidad;
            producto.setStock(nuevoStock);
            productoRepository.save(producto);

            HistoricoMovimiento movimiento = new HistoricoMovimiento();

            movimiento.setProducto(producto);
            movimiento.setTipoMovimiento("ENTRADA");
            movimiento.setCantidad(cantidad);
            movimiento.setFechaMovimiento(LocalDateTime.now());

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String correo = auth.getName();
            Usuario usuario = usuarioRepository.findByCorreo(correo).orElseThrow();
            movimiento.setUsuario(usuario);
            historicoMovimientoService.guardarMovimiento(movimiento);
        }
    }


    // disminuir inventario
    @Override
    public void disminuirInventario(Integer idProducto, Integer cantidad) {
        if (cantidad <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }

        Producto producto = buscarPorId(idProducto);

        if (producto != null) {
            if ( cantidad > producto.getStock()) {
                throw new RuntimeException("No hay sufiente inventario");
            }

            int nuevoStock = producto.getStock() - cantidad;
            producto.setStock(nuevoStock);
            productoRepository.save(producto);

            HistoricoMovimiento movimiento = new HistoricoMovimiento();

            movimiento.setProducto(producto);
            movimiento.setTipoMovimiento("SALIDA");
            movimiento.setCantidad(cantidad);
            movimiento.setFechaMovimiento(LocalDateTime.now());

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String correo = auth.getName();
            Usuario usuario = usuarioRepository.findByCorreo(correo).orElseThrow();
            movimiento.setUsuario(usuario);

            historicoMovimientoService.guardarMovimiento(movimiento);
        }
    }
}
