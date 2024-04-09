package com.pruebas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MODIFICADORES")
public class Modificadores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Modificador", nullable = false)
    private Long idModificador;

    @Column(name = "Nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "Precio_Adicional", nullable = false)
    private Double precioAdicional;

    public Long getIdModificador() {
        return idModificador;
    }

    public void setIdModificador(Long idModificador) {
        this.idModificador = idModificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecioAdicional() {
        return precioAdicional;
    }

    public void setPrecioAdicional(Double precioAdicional) {
        this.precioAdicional = precioAdicional;
    }

    // Getters y setters
}