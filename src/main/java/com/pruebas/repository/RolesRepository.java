package com.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebas.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {

}