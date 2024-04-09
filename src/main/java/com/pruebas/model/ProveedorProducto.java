package com.pruebas.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROVEEDORES_PRODUCTOS")
public class ProveedorProducto {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROVEEDOR_PRODUCTOS", nullable = false)
    private ProveedorProductoId id;

    @ManyToOne
    @JoinColumn(name = "ID_Producto", insertable = false, updatable = false)
    private Productos producto;

    @ManyToOne
    @JoinColumn(name = "ID_Proveedor", insertable = false, updatable = false)
    private Proveedores proveedor;

    @Column(name = "Cantidad", nullable = false)
    private int cantidad;

    // Getters y Setters

    public ProveedorProductoId getId() {
        return id;
    }

    public void setId(ProveedorProductoId id) {
        this.id = id;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
