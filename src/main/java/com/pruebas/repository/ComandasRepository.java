package com.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebas.model.Comandas;

public interface ComandasRepository extends JpaRepository<Comandas, Long> {

}