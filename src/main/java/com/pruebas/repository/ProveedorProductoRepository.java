package com.pruebas.repository;

import com.pruebas.model.ProveedorProducto;
import com.pruebas.model.ProveedorProductoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorProductoRepository extends JpaRepository<ProveedorProducto, ProveedorProductoId> {
}
