package com.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebas.model.Mesas;

public interface MesasRepository extends JpaRepository<Mesas, Long> {

}