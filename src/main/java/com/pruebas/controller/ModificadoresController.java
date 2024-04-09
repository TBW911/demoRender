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

import com.pruebas.model.Modificadores;
import com.pruebas.exception.ResourceNotFoundException;
import com.pruebas.repository.ModificadoresRepository;

@RestController
@RequestMapping("/bdoklans")
public class ModificadoresController {

    @Autowired
    private ModificadoresRepository modificadoresRepository;

    @GetMapping("/modificadores")
    public List<Modificadores> imprimirListaModificadores() {
        return modificadoresRepository.findAll();
    }

    @GetMapping("/modificadores/{id}")
    public ResponseEntity<Modificadores> buscarModificadorPorId(@PathVariable(value = "id") Long idModificador)
            throws ResourceNotFoundException {
        Modificadores modificador = modificadoresRepository.findById(idModificador)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un modificador para el id :: " + idModificador));
        return ResponseEntity.ok().body(modificador);
    }

    @PostMapping("/modificadores")
    public Modificadores agregarModificador(@RequestBody Modificadores modificador) {
        return modificadoresRepository.save(modificador);
    }

    @PutMapping("/modificadores/{id}")
    public ResponseEntity<Modificadores> actualizarModificador(@PathVariable(value = "id") Long idModificador,
            @RequestBody Modificadores datosModificador) throws ResourceNotFoundException {
        Modificadores modificador = modificadoresRepository.findById(idModificador)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un modificador para el id :: " + idModificador));

        modificador.setNombre(datosModificador.getNombre());
        modificador.setPrecioAdicional(datosModificador.getPrecioAdicional());

        final Modificadores modificadorActualizado = modificadoresRepository.save(modificador);
        return ResponseEntity.ok(modificadorActualizado);
    }

    @DeleteMapping("/modificadores/{id}")
    public Map<String, Boolean> eliminarModificador(@PathVariable(value = "id") Long idModificador)
            throws ResourceNotFoundException {
        Modificadores modificador = modificadoresRepository.findById(idModificador)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un modificador con el id :: " + idModificador));

        modificadoresRepository.delete(modificador);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}