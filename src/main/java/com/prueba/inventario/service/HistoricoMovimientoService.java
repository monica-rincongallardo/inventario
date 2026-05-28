package com.prueba.inventario.service;

import com.prueba.inventario.entity.HistoricoMovimiento;

import java.util.List;

// Interface para lógica de historial de movimientos
public interface HistoricoMovimientoService {
    // listar todos los movimientos
    List<HistoricoMovimiento> listarMovimientos();

    // guardar movimiento
    void guardarMovimiento(HistoricoMovimiento movimiento);

    // filtar movimientos
    List<HistoricoMovimiento> filtrarPorTipo(String tipoMovimiento);
}
