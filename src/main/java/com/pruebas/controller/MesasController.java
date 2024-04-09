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

import com.pruebas.model.Mesas;
import com.pruebas.exception.ResourceNotFoundException;
import com.pruebas.repository.MesasRepository;

@RestController
@RequestMapping("/bdoklans")
public class MesasController {

    @Autowired
    private MesasRepository mesasRepository;

    @GetMapping("/mesas")
    public List<Mesas> imprimirListaMesas() {
        return mesasRepository.findAll();
    }

    @GetMapping("/mesas/{id}")
    public ResponseEntity<Mesas> buscarMesaPorId(@PathVariable(value = "id") Long idMesa)
            throws ResourceNotFoundException {
        Mesas mesa = mesasRepository.findById(idMesa)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una mesa para el id :: " + idMesa));
        return ResponseEntity.ok().body(mesa);
    }

    @PostMapping("/mesas")
    public Mesas agregarMesa(@RequestBody Mesas mesa) {
        return mesasRepository.save(mesa);
    }

    @PutMapping("/mesas/{id}")
    public ResponseEntity<Mesas> actualizarMesa(@PathVariable(value = "id") Long idMesa,
            @RequestBody Mesas datosMesa) throws ResourceNotFoundException {
        Mesas mesa = mesasRepository.findById(idMesa)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una mesa para el id :: " + idMesa));

        mesa.setEstatus(datosMesa.isEstatus());
        mesa.setLugar(datosMesa.getLugar());

        final Mesas mesaActualizada = mesasRepository.save(mesa);
        return ResponseEntity.ok(mesaActualizada);
    }

    @DeleteMapping("/mesas/{id}")
    public Map<String, Boolean> eliminarMesa(@PathVariable(value = "id") Long idMesa)
            throws ResourceNotFoundException {
        Mesas mesa = mesasRepository.findById(idMesa)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una mesa con el id :: " + idMesa));

        mesasRepository.delete(mesa);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}

