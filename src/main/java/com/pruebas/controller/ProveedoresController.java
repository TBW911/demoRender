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

import com.pruebas.model.Proveedores;
import com.pruebas.exception.ResourceNotFoundException;
import com.pruebas.repository.ProveedoresRepository;

@RestController
@RequestMapping("/bdoklans")
public class ProveedoresController {

    @Autowired
    private ProveedoresRepository proveedoresRepository;

    @GetMapping("/proveedores")
    public List<Proveedores> imprimirListaProveedores() {
        return proveedoresRepository.findAll();
    }

    @GetMapping("/proveedores/{id}")
    public ResponseEntity<Proveedores> buscarProveedorPorId(@PathVariable(value = "id") Long idProveedor)
            throws ResourceNotFoundException {
        Proveedores proveedor = proveedoresRepository.findById(idProveedor)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un proveedor para el id :: " + idProveedor));
        return ResponseEntity.ok().body(proveedor);
    }

    @PostMapping("/proveedores")
    public Proveedores agregarProveedor(@RequestBody Proveedores proveedor) {
        return proveedoresRepository.save(proveedor);
    }

    @PutMapping("/proveedores/{id}")
    public ResponseEntity<Proveedores> actualizarProveedor(@PathVariable(value = "id") Long idProveedor,
            @RequestBody Proveedores datosProveedor) throws ResourceNotFoundException {
        Proveedores proveedor = proveedoresRepository.findById(idProveedor)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un proveedor para el id :: " + idProveedor));

        proveedor.setEmpleado(datosProveedor.getEmpleado());
        proveedor.setRfc(datosProveedor.getRfc());
        proveedor.setNombre(datosProveedor.getNombre());
        proveedor.setRazonSocial(datosProveedor.getRazonSocial());
        proveedor.setCorreoElectronico(datosProveedor.getCorreoElectronico());
        proveedor.setTelefono(datosProveedor.getTelefono());
        proveedor.setCp(datosProveedor.getCp());
        proveedor.setCalle(datosProveedor.getCalle());
        proveedor.setNumero(datosProveedor.getNumero());
        proveedor.setColonia(datosProveedor.getColonia());
        proveedor.setAvenida(datosProveedor.getAvenida());
        proveedor.setCiudad(datosProveedor.getCiudad());
        proveedor.setEstado(datosProveedor.getEstado());

        final Proveedores proveedorActualizado = proveedoresRepository.save(proveedor);
        return ResponseEntity.ok(proveedorActualizado);
    }

    @DeleteMapping("/proveedores/{id}")
    public Map<String, Boolean> eliminarProveedor(@PathVariable(value = "id") Long idProveedor)
            throws ResourceNotFoundException {
        Proveedores proveedor = proveedoresRepository.findById(idProveedor)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un proveedor con el id :: " + idProveedor));

        proveedoresRepository.delete(proveedor);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
