package com.pruebas.controller;

import com.pruebas.model.PedidosModificadores;
import com.pruebas.model.PedidosModificadoresId;
import com.pruebas.exception.ResourceNotFoundException;
import com.pruebas.repository.PedidosModificadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bdoklans")
public class PedidosModificadoresController {

    @Autowired
    private PedidosModificadoresRepository pedidosModificadoresRepository;

    @GetMapping("/pedidos-modificadores")
    public List<PedidosModificadores> getAllPedidosModificadores() {
        return pedidosModificadoresRepository.findAll();
    }

    @GetMapping("/pedidos-modificadores/{idPedido}/{idModificador}")
    public ResponseEntity<PedidosModificadores> getPedidosModificadoresById(
            @PathVariable Long idPedido, @PathVariable Long idModificador) throws ResourceNotFoundException {
        PedidosModificadores pedidoModificadores = pedidosModificadoresRepository
                .findById(new PedidosModificadoresId(idPedido, idModificador))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró un pedido modificador para el ID Pedido :: " + idPedido +
                        " y el ID Modificador :: " + idModificador));
        return ResponseEntity.ok().body(pedidoModificadores);
    }

    @PostMapping("/pedidos-modificadores")
    public PedidosModificadores addPedidosModificadores(@RequestBody PedidosModificadores pedidoModificadores) {
        return pedidosModificadoresRepository.save(pedidoModificadores);
    }

    @PutMapping("/pedidos-modificadores/{idPedido}/{idModificador}")
    public ResponseEntity<PedidosModificadores> updatePedidosModificadores(
            @PathVariable Long idPedido, @PathVariable Long idModificador,
            @RequestBody PedidosModificadores updatedPedidoModificadores) throws ResourceNotFoundException {
        PedidosModificadores pedidoModificadores = pedidosModificadoresRepository
                .findById(new PedidosModificadoresId(idPedido, idModificador))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró un pedido modificador para el ID Pedido :: " + idPedido +
                                " y el ID Modificador :: " + idModificador));
        pedidoModificadores.setPedido(updatedPedidoModificadores.getPedido());
        pedidoModificadores.setModificador(updatedPedidoModificadores.getModificador());

        final PedidosModificadores updatedPedidoModificadoresEntity = pedidosModificadoresRepository.save(pedidoModificadores);
        return ResponseEntity.ok(updatedPedidoModificadoresEntity);
    }




    @DeleteMapping("/pedidos-modificadores/{idPedido}/{idModificador}")
    public Map<String, Boolean> deletePedidosModificadores(
            @PathVariable Long idPedido, @PathVariable Long idModificador) throws ResourceNotFoundException {
        PedidosModificadores pedidoModificadores = pedidosModificadoresRepository
                .findById(new PedidosModificadoresId(idPedido, idModificador))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró un pedido modificador para el ID Pedido :: " + idPedido +
                        " y el ID Modificador :: " + idModificador));

        pedidosModificadoresRepository.delete(pedidoModificadores);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
