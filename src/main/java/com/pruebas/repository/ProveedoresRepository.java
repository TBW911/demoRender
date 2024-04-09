package com.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebas.model.Proveedores;

public interface ProveedoresRepository extends JpaRepository<Proveedores, Long> {

}