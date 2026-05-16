package Modelo;

import java.util.Objects;

public class Grado {

    private Integer idGrado;
    private String nombre;
    private String nivel;

    public Grado() {
    }

    public Grado(String nombre, String nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public Grado(Integer idGrado, String nombre, String nivel) {
        this.idGrado = idGrado;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public Integer getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return nombre + " - " + nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grado)) return false;
        Grado grado = (Grado) o;
        return Objects.equals(idGrado, grado.idGrado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGrado);
    }
}
