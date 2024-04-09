package com.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebas.model.Empleados;

public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {

}