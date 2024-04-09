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

import com.pruebas.model.Roles;
import com.pruebas.exception.ResourceNotFoundException;
import com.pruebas.repository.RolesRepository;

@RestController
@RequestMapping("/bdoklans")
public class RolesController {

    @Autowired
    private RolesRepository rolesRepository;

    @GetMapping("/roles")
    public List<Roles> imprimirListaRoles() {
        return rolesRepository.findAll();
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Roles> buscarRolPorId(@PathVariable(value = "id") Long idRol)
            throws ResourceNotFoundException {
        Roles rol = rolesRepository.findById(idRol)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un rol para el id :: " + idRol));
        return ResponseEntity.ok().body(rol);
    }

    @PostMapping("/roles")
    public Roles agregarRol(@RequestBody Roles rol) {
        return rolesRepository.save(rol);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Roles> actualizarRol(@PathVariable(value = "id") Long idRol,
            @RequestBody Roles datosRol) throws ResourceNotFoundException {
        Roles rol = rolesRepository.findById(idRol)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un rol para el id :: " + idRol));

        rol.setRol(datosRol.getRol());

        final Roles rolActualizado = rolesRepository.save(rol);
        return ResponseEntity.ok(rolActualizado);
    }

    @DeleteMapping("/roles/{id}")
    public Map<String, Boolean> eliminarRol(@PathVariable(value = "id") Long idRol)
            throws ResourceNotFoundException {
        Roles rol = rolesRepository.findById(idRol)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un rol con el id :: " + idRol));

        rolesRepository.delete(rol);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}