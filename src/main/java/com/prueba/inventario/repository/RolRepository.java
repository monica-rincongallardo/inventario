package com.prueba.inventario.repository;

import com.prueba.inventario.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository para la entidad Rol
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
