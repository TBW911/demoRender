package com.pruebas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROVEEDORES")
public class Proveedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Proveedor", nullable = false)
    private Long idProveedor;

    @ManyToOne
    @JoinColumn(name = "ID_Empleado", nullable = false, foreignKey = @ForeignKey(name = "FK_PROVEEDORES_EMPLEADOS"))
    private Empleados empleado;

    @Column(name = "RFC", unique = true, nullable = false, length = 13)
    private String rfc;

    @Column(name = "Nombre", nullable = false, length = 25)
    private String nombre;

    @Column(name = "RazonSocial", length = 40)
    private String razonSocial;

    @Column(name = "CorreoElectronico", unique = true, nullable = false, length = 50)
    private String correoElectronico;

    @Column(name = "Telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "Cp", nullable = false, length = 6)
    private String cp;

    @Column(name = "Calle", nullable = false, length = 13)
    private String calle;

    @Column(name = "Numero", length = 10)
    private String numero;

    @Column(name = "Colonia", nullable = false, length = 13)
    private String colonia;

    @Column(name = "Avenida", nullable = false, length = 13)
    private String avenida;

    @Column(name = "Ciudad", nullable = false, length = 13)
    private String ciudad;

    @Column(name = "Estado", nullable = false, length = 13)
    private String estado;

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getAvenida() {
        return avenida;
    }

    public void setAvenida(String avenida) {
        this.avenida = avenida;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Getters y setters
}
