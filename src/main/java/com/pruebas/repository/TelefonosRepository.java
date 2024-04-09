package com.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebas.model.Telefonos;

public interface TelefonosRepository extends JpaRepository<Telefonos, Long> {

}