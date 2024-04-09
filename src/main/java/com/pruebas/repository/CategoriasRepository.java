package com.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebas.model.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Long> {

}