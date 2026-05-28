package com.prueba.inventario.repository;

import com.prueba.inventario.entity.HistoricoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository para la entidad HistoricoMovimiento
@Repository
public interface HistoricoMovimientoRepository extends JpaRepository<HistoricoMovimiento, Integer> {
    List<HistoricoMovimiento> findByTipoMovimiento (String tipoMovimiento);
}
