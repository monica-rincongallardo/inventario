package com.prueba.inventario.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Entidad que representa los roles del sistema: Administrador y Almacenista

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Rol {
    // llave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRol")
    private Integer idRol;

    // nombre del rol
    @Column(name = "nombreRol", nullable = false, length = 50)
    private String nombreRol;
}
