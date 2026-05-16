package Modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Alumno {

    private Integer idAlumno;
    private String dni;
    private String nombre;
    private Apoderado apoderado;
    private LocalDate fechaNacimiento;
    private boolean antecedente;

    public Alumno() {
    }

    public Alumno(String dni, String nombre, Apoderado apoderado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apoderado = apoderado;
    }

    public Alumno(Integer idAlumno, String dni, String nombre, Apoderado apoderado, LocalDate fechaNacimiento, boolean antecedente) {
        this.idAlumno = idAlumno;
        this.dni = dni;
        this.nombre = nombre;
        this.apoderado = apoderado;
        this.fechaNacimiento = fechaNacimiento;
        this.antecedente = antecedente;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
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

    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isAntecedente() {
        return antecedente;
    }

    public void setAntecedente(boolean antecedente) {
        this.antecedente = antecedente;
    }

    @Override
    public String toString() {
        return nombre + " - DNI: " + dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno)) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(idAlumno, alumno.idAlumno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlumno);
    }
}
