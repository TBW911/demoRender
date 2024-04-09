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

import com.pruebas.model.Comandas;
import com.pruebas.exception.ResourceNotFoundException;
import com.pruebas.repository.ComandasRepository;

@RestController
@RequestMapping("/bdoklans")
public class ComandasController {

    @Autowired
    private ComandasRepository comandasRepository;

    @GetMapping("/comandas")
    public List<Comandas> imprimirListaComandas() {
        return comandasRepository.findAll();
    }

    @GetMapping("/comandas/{id}")
    public ResponseEntity<Comandas> buscarComandaPorId(@PathVariable(value = "id") Long idComanda)
            throws ResourceNotFoundException {
        Comandas comanda = comandasRepository.findById(idComanda)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una comanda para el id :: " + idComanda));
        return ResponseEntity.ok().body(comanda);
    }

    @PostMapping("/comandas")
    public Comandas agregarComanda(@RequestBody Comandas comanda) {
        return comandasRepository.save(comanda);
    }

    @PutMapping("/comandas/{id}")
    public ResponseEntity<Comandas> actualizarComanda(@PathVariable(value = "id") Long idComanda,
            @RequestBody Comandas datosComanda) throws ResourceNotFoundException {
        Comandas comanda = comandasRepository.findById(idComanda)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una comanda para el id :: " + idComanda));

        comanda.setPedido(datosComanda.getPedido());
        comanda.setFecha(datosComanda.getFecha());
        comanda.setMontoFinal(datosComanda.getMontoFinal());

        final Comandas comandaActualizada = comandasRepository.save(comanda);
        return ResponseEntity.ok(comandaActualizada);
    }

    @DeleteMapping("/comandas/{id}")
    public Map<String, Boolean> eliminarComanda(@PathVariable(value = "id") Long idComanda)
            throws ResourceNotFoundException {
        Comandas comanda = comandasRepository.findById(idComanda)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una comanda con el id :: " + idComanda));

        comandasRepository.delete(comanda);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}