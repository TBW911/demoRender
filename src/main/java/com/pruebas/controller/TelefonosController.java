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

import com.pruebas.model.Telefonos;
import com.pruebas.exception.ResourceNotFoundException;
import com.pruebas.repository.TelefonosRepository;

@RestController
@RequestMapping("/bdoklans")
public class TelefonosController {

    @Autowired
    private TelefonosRepository telefonosRepository;

    @GetMapping("/telefonos")
    public List<Telefonos> imprimirListaTelefonos() {
        return telefonosRepository.findAll();
    }

    @GetMapping("/telefonos/{id}")
    public ResponseEntity<Telefonos> buscarTelefonoPorId(@PathVariable(value = "id") Long idTelefono)
            throws ResourceNotFoundException {
        Telefonos telefono = telefonosRepository.findById(idTelefono)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un teléfono para el id :: " + idTelefono));
        return ResponseEntity.ok().body(telefono);
    }

    @PostMapping("/telefonos")
    public Telefonos agregarTelefono(@RequestBody Telefonos telefono) {
        return telefonosRepository.save(telefono);
    }

    @PutMapping("/telefonos/{id}")
    public ResponseEntity<Telefonos> actualizarTelefono(@PathVariable(value = "id") Long idTelefono,
            @RequestBody Telefonos datosTelefono) throws ResourceNotFoundException {
        Telefonos telefono = telefonosRepository.findById(idTelefono)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un teléfono para el id :: " + idTelefono));

        telefono.setProveedor(datosTelefono.getProveedor());
        telefono.setTelefono(datosTelefono.getTelefono());

        final Telefonos telefonoActualizado = telefonosRepository.save(telefono);
        return ResponseEntity.ok(telefonoActualizado);
    }

    @DeleteMapping("/telefonos/{id}")
    public Map<String, Boolean> eliminarTelefono(@PathVariable(value = "id") Long idTelefono)
            throws ResourceNotFoundException {
        Telefonos telefono = telefonosRepository.findById(idTelefono)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un teléfono con el id :: " + idTelefono));

        telefonosRepository.delete(telefono);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
