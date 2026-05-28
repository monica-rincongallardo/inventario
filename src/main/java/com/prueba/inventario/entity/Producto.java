package com.prueba.inventario.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Entidad que representa los productos del inventario

@Entity
@Table(name = "productos")
@Getter
@Setter
public class Producto {
    // llave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Integer idProducto;

    // nombre del producto
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    // descripción del producto
    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    // precio del producto
    @Column(name = "precio", nullable = false)
    private Double precio;

    // cantidad disponible en inventario (cantidad inicial 0)
    @Column(name = "stock", nullable = false)
    private Integer stock;

    /*
     estatus del producto:
     1 = activo
     0 = inactivo
    */
    @Column(name = "estatus", nullable = false)
    private Integer estatus;
}
