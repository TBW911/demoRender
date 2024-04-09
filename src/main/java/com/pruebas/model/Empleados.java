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
import java.util.Date;

@Entity
@Table(name = "EMPLEADOS")
public class Empleados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Empleado", nullable = false)
    private Long idEmpleado;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_EMPLEADOS_USUARIOS"))
    private Usuarios usuario;

    @Column(name = "Nombre", nullable = false, length = 25)
    private String nombre;

    @Column(name = "Apellido_P", nullable = false, length = 15)
    private String apellidoP;

    @Column(name = "Apellido_M", nullable = false, length = 15)
    private String apellidoM;

    @Column(name = "RFC", unique = true, nullable = false, length = 13)
    private String rfc;

    @Column(name = "CorreoElectronico", unique = true, nullable = false, length = 50)
    private String correoElectronico;

    @Column(name = "Telefono", unique = true, nullable = false, length = 10)
    private String telefono;

    @Column(name = "FechaNacimiento", nullable = false)
    private Date fechaNacimiento;

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters y setters
}
