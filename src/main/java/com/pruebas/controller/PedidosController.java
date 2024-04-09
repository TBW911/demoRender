package com.pruebas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebas.model.Pedidos;
import com.pruebas.exception.ResourceNotFoundException;
import com.pruebas.repository.PedidosRepository;

@RestController
@RequestMapping("/bdoklans")
public class PedidosController {

    @Autowired
    private PedidosRepository pedidosRepository;

    @GetMapping("/pedidos")
    public List<Pedidos> imprimirListaPedidos() {
        return pedidosRepository.findAll();
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<Pedidos> buscarPedidoPorId(@PathVariable(value = "id") Long idPedido)
            throws ResourceNotFoundException {
        Pedidos pedido = pedidosRepository.findById(idPedido)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un pedido para el id :: " + idPedido));
        return ResponseEntity.ok().body(pedido);
    }

    @PostMapping("/pedidos")
    public Pedidos agregarPedido(@RequestBody Pedidos pedido) {
        return pedidosRepository.save(pedido);
    }

    @PutMapping("/pedidos/{id}")
    public ResponseEntity<Pedidos> actualizarPedido(@PathVariable(value = "id") Long idPedido,
            @RequestBody Pedidos datosPedido) throws ResourceNotFoundException {
        Pedidos pedido = pedidosRepository.findById(idPedido)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un pedido para el id :: " + idPedido));

        pedido.setEmpleado(datosPedido.getEmpleado());
        pedido.setProducto(datosPedido.getProducto());
        pedido.setMesa(datosPedido.getMesa());
        pedido.setEstatus(datosPedido.isEstatus());
        pedido.setFecha(datosPedido.getFecha());
        pedido.setNota(datosPedido.getNota());

        final Pedidos pedidoActualizado = pedidosRepository.save(pedido);
        return ResponseEntity.ok(pedidoActualizado);
    }

    @DeleteMapping("/pedidos/{id}")
    public Map<String, Boolean> eliminarPedido(@PathVariable(value = "id") Long idPedido)
            throws ResourceNotFoundException {
        Pedidos pedido = pedidosRepository.findById(idPedido)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un pedido con el id :: " + idPedido));

        pedidosRepository.delete(pedido);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
