package com.pruebas.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProveedorProductoId implements Serializable {

	   @Column(name = "ID_Producto")
	    private Long idProducto;

	    @Column(name = "ID_Proveedor")
	    private Long idProveedor;

    public ProveedorProductoId() {
    }

    // Constructor con argumentos
    public ProveedorProductoId(Long idProducto, Long idProveedor) {
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
    }

    // Getters y Setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProveedorProductoId that = (ProveedorProductoId) o;
        return idProducto.equals(that.idProducto) &&
                idProveedor.equals(that.idProveedor);
    }

    @Override
    public int hashCode() {
        return idProducto.hashCode() + idProveedor.hashCode();
    }
}
