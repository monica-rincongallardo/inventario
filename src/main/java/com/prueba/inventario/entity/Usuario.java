package com.prueba.inventario.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Entidad que representa los usuarios del sistema

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {
    // llave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer idUsuario;

    // nombre del usuario
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    // correo del usuario
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    // contraseña del usuario
    @Column(name = "contrasena", nullable = false, length = 25)
    private String contrasena;

    /*
     estatus del usuario:
     1 = activo
     0 = inactivo
    */
    @Column(name = "estatus", nullable = false)
    private Integer estatus;

    // relación muchos a uno: muchos usuarios pueden tener un mismo rol
    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;
}
