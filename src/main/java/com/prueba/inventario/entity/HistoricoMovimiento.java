package com.prueba.inventario.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// Entidad que representa el historial de movimientos

@Entity
@Table(name = "historico_movimientos")
@Getter
@Setter
public class HistoricoMovimiento {
    // llave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMovimiento")
    private Integer idMovimiento;

    // producto relacionado con el movimiento
    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    // usuario que realizó el movimiento
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    // tipo de movimiento: entrada o salida
    @Column(name = "tipoMovimiento", nullable = false, length = 20)
    private String tipoMovimiento;

    // cantidad de productos movidos
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    // fecha y hora del movimiento
    @Column(name = "fechaMovimiento", nullable = false)
    private LocalDateTime fechaMovimiento;
}
