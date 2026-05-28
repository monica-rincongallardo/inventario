package com.prueba.inventario.service.impl;

import com.prueba.inventario.entity.HistoricoMovimiento;
import com.prueba.inventario.repository.HistoricoMovimientoRepository;
import com.prueba.inventario.service.HistoricoMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Implementación de la lógica de historial de movimientos

@Service
public class HistoricoMovimientoServiceImpl implements HistoricoMovimientoService {
    @Autowired
    private HistoricoMovimientoRepository historicoMovimientoRepository;

    // obtener todos los movimientos
    @Override
    public List<HistoricoMovimiento> listarMovimientos() {
        return historicoMovimientoRepository.findAll();
    }

    // guardar movimiento
    @Override
    public void guardarMovimiento(HistoricoMovimiento movimiento) {
        historicoMovimientoRepository.save(movimiento);
    }

    // filtrar historial por movimiento
    @Override
    public List<HistoricoMovimiento> filtrarPorTipo(String tipoMovimiento) {
        return historicoMovimientoRepository.findByTipoMovimiento(tipoMovimiento);
    }
}
