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

import com.pruebas.model.Categorias;
import com.pruebas.exception.ResourceNotFoundException;
import com.pruebas.repository.CategoriasRepository;

@RestController
@RequestMapping("/bdoklans")
public class CategoriasController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @GetMapping("/categorias")
    public List<Categorias> imprimirListaCategorias() {
        return categoriasRepository.findAll();
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categorias> buscarCategoriasId(@PathVariable(value = "id") Long idCategoria)
            throws ResourceNotFoundException {
        Categorias categorias = categoriasRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una categoría para el id :: " + idCategoria));
        return ResponseEntity.ok().body(categorias);
    }

    @PostMapping("/categorias")
    public Categorias agregarCategorias(@RequestBody Categorias categorias) {
        return categoriasRepository.save(categorias);
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categorias> actualizarCategorias(@PathVariable(value = "id") Long idCategoria,
            @RequestBody Categorias datosCategorias) throws ResourceNotFoundException {
        Categorias categorias = categoriasRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una categoría para el id :: " + idCategoria));

        categorias.setNombre(datosCategorias.getNombre());
        categorias.setDescripcion(datosCategorias.getDescripcion());

        final Categorias categoriasActualizado = categoriasRepository.save(categorias);
        return ResponseEntity.ok(categoriasActualizado);
    }

    @DeleteMapping("/categorias/{id}")
    public Map<String, Boolean> eliminarCategorias(@PathVariable(value = "id") Long idCategoria)
            throws ResourceNotFoundException {
        Categorias categorias = categoriasRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una categoría con el id :: " + idCategoria));

        categoriasRepository.delete(categorias);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}