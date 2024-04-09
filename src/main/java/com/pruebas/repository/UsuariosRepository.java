package com.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebas.model.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

}