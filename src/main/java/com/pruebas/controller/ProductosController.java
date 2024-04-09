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

import com.pruebas.model.Productos;
import com.pruebas.exception.ResourceNotFoundException;
import com.pruebas.repository.ProductosRepository;

@RestController
@RequestMapping("/bdoklans")
public class ProductosController {

    @Autowired
    private ProductosRepository productosRepository;

    @GetMapping("/productos")
    public List<Productos> imprimirListaProductos() {
        return productosRepository.findAll();
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Productos> buscarProductoPorId(@PathVariable(value = "id") Long idProducto)
            throws ResourceNotFoundException {
        Productos producto = productosRepository.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un producto para el id :: " + idProducto));
        return ResponseEntity.ok().body(producto);
    }

    @PostMapping("/productos")
    public Productos agregarProducto(@RequestBody Productos producto) {
        return productosRepository.save(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Productos> actualizarProducto(@PathVariable(value = "id") Long idProducto,
            @RequestBody Productos datosProducto) throws ResourceNotFoundException {
        Productos producto = productosRepository.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un producto para el id :: " + idProducto));

        producto.setProveedor(datosProducto.getProveedor());
        producto.setCategoria(datosProducto.getCategoria());
        producto.setNombre(datosProducto.getNombre());
        producto.setDescripcion(datosProducto.getDescripcion());
        producto.setPrecio(datosProducto.getPrecio());

        final Productos productoActualizado = productosRepository.save(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/productos/{id}")
    public Map<String, Boolean> eliminarProducto(@PathVariable(value = "id") Long idProducto)
            throws ResourceNotFoundException {
        Productos producto = productosRepository.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un producto con el id :: " + idProducto));

        productosRepository.delete(producto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
