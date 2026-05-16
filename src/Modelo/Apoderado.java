package Modelo;

import java.util.Objects;

public class Apoderado {

    private Integer idApoderado;
    private String dni;
    private String nombre;
    private String telefono;
    private String direccion;
    private String email;

    public Apoderado() {
    }

    public Apoderado(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public Apoderado(Integer idApoderado, String dni, String nombre, String telefono, String direccion, String email) {
        this.idApoderado = idApoderado;
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }

    public Integer getIdApoderado() {
        return idApoderado;
    }

    public void setIdApoderado(Integer idApoderado) {
        this.idApoderado = idApoderado;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nombre + " - DNI: " + dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apoderado)) return false;
        Apoderado apoderado = (Apoderado) o;
        return Objects.equals(idApoderado, apoderado.idApoderado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idApoderado);
    }
}
