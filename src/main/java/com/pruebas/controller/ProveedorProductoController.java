package com.pruebas.controller;

import com.pruebas.model.ProveedorProducto;
import com.pruebas.model.ProveedorProductoId;
import com.pruebas.exception.ResourceNotFoundException;
import com.pruebas.repository.ProveedorProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bdoklans/proveedores-productos")
public class ProveedorProductoController {

    @Autowired
    private ProveedorProductoRepository proveedorProductoRepository;

    @GetMapping
    public List<ProveedorProducto> getAllProveedorProductos() {
        return proveedorProductoRepository.findAll();
    }

    @GetMapping("/{idProducto}/{idProveedor}")
    public ResponseEntity<ProveedorProducto> getProveedorProductoById(
            @PathVariable Long idProducto, @PathVariable Long idProveedor) throws ResourceNotFoundException {
        ProveedorProducto proveedorProducto = proveedorProductoRepository
                .findById(new ProveedorProductoId(idProducto, idProveedor))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró un proveedor producto para el ID Producto :: " + idProducto +
                                " y el ID Proveedor :: " + idProveedor));

        return ResponseEntity.ok().body(proveedorProducto);
    }

    @PostMapping
    public ProveedorProducto createProveedorProducto(@RequestBody ProveedorProducto proveedorProducto) {
        return proveedorProductoRepository.save(proveedorProducto);
    }

    @PutMapping("/{idProducto}/{idProveedor}")
    public ResponseEntity<ProveedorProducto> updateProveedorProducto(
            @PathVariable Long idProducto, @PathVariable Long idProveedor,
            @RequestBody ProveedorProducto updatedProveedorProducto) throws ResourceNotFoundException {

        // Crear una instancia de ProveedorProductoId
        ProveedorProductoId proveedorProductoId = new ProveedorProductoId(idProducto, idProveedor);

        // Obtener la entidad existente
        ProveedorProducto proveedorProducto = proveedorProductoRepository
                .findById(proveedorProductoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró un proveedor producto para el ID Producto :: " + idProducto +
                                " y el ID Proveedor :: " + idProveedor));

        // Actualizar campos según tus necesidades
        proveedorProducto.setCantidad(updatedProveedorProducto.getCantidad());

        final ProveedorProducto updatedEntity = proveedorProductoRepository.save(proveedorProducto);
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/{idProducto}/{idProveedor}")
    public Map<String, Boolean> deleteProveedorProducto(
            @PathVariable Long idProducto, @PathVariable Long idProveedor) throws ResourceNotFoundException {
        ProveedorProductoId proveedorProductoId = new ProveedorProductoId(idProducto, idProveedor);
        ProveedorProducto proveedorProducto = proveedorProductoRepository
                .findById(proveedorProductoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró un proveedor producto para el ID Producto :: " + idProducto +
                                " y el ID Proveedor :: " + idProveedor));

        proveedorProductoRepository.delete(proveedorProducto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
