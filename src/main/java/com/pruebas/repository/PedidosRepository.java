package com.pruebas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebas.model.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {

}