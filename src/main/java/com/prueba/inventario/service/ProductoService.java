package com.prueba.inventario.service;

import com.prueba.inventario.entity.Producto;

import java.util.List;

// Interface para lógica de productos
public interface ProductoService {
    // obtener todos los productos
    List<Producto> obtenerTodos();

    // guardar un nuevo producto
    Producto guardarProducto(Producto producto);

    // buscar producto por id
    Producto buscarPorId(Integer id);

    // cambiar estatus del producto
    void cambiarEstatus(Integer id);

    // aumentar inventario
    void aumentarInventario(Integer idProducto, Integer cantidad);

    // disminuir inventario
    void disminuirInventario(Integer idProducto, Integer cantidad);

}
