package com.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebas.model.PedidosModificadores;
import com.pruebas.model.PedidosModificadoresId;

public interface PedidosModificadoresRepository extends JpaRepository<PedidosModificadores, PedidosModificadoresId> {

    // Puedes agregar métodos personalizados si es necesario
}
