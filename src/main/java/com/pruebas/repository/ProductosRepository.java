package com.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebas.model.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Long> {

}