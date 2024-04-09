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

import com.pruebas.model.Empleados;
import com.pruebas.exception.ResourceNotFoundException;
import com.pruebas.repository.EmpleadosRepository;

@RestController
@RequestMapping("/bdoklans")
public class EmpleadosController {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    @GetMapping("/empleados")
    public List<Empleados> imprimirListaEmpleados() {
        return empleadosRepository.findAll();
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleados> buscarEmpleadoPorId(@PathVariable(value = "id") Long idEmpleado)
            throws ResourceNotFoundException {
        Empleados empleado = empleadosRepository.findById(idEmpleado)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el id :: " + idEmpleado));
        return ResponseEntity.ok().body(empleado);
    }

    @PostMapping("/empleados")
    public Empleados agregarEmpleado(@RequestBody Empleados empleado) {
        return empleadosRepository.save(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleados> actualizarEmpleado(@PathVariable(value = "id") Long idEmpleado,
            @RequestBody Empleados datosEmpleado) throws ResourceNotFoundException {
        Empleados empleado = empleadosRepository.findById(idEmpleado)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el id :: " + idEmpleado));

        empleado.setUsuario(datosEmpleado.getUsuario());
        empleado.setNombre(datosEmpleado.getNombre());
        empleado.setApellidoP(datosEmpleado.getApellidoP());
        empleado.setApellidoM(datosEmpleado.getApellidoM());
        empleado.setRfc(datosEmpleado.getRfc());
        empleado.setCorreoElectronico(datosEmpleado.getCorreoElectronico());
        empleado.setTelefono(datosEmpleado.getTelefono());
        empleado.setFechaNacimiento(datosEmpleado.getFechaNacimiento());

        final Empleados empleadoActualizado = empleadosRepository.save(empleado);
        return ResponseEntity.ok(empleadoActualizado);
    }

    @DeleteMapping("/empleados/{id}")
    public Map<String, Boolean> eliminarEmpleado(@PathVariable(value = "id") Long idEmpleado)
            throws ResourceNotFoundException {
        Empleados empleado = empleadosRepository.findById(idEmpleado)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado con el id :: " + idEmpleado));

        empleadosRepository.delete(empleado);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
