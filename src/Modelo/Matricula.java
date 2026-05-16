package Modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Matricula {

    public static final String ESTADO_PENDIENTE = "pendiente";
    public static final String ESTADO_PAGADO = "pagado";

    private Integer idMatricula;
    private Alumno alumno;
    private Apoderado apoderado;
    private Grado grado;
    private Vacante vacante;
    private LocalDate fechaMatricula;
    private String estado;
    private LocalDateTime fechaRegistro;

    public Matricula() {
    }

    public Matricula(Alumno alumno, Apoderado apoderado, Grado grado, Vacante vacante) {
        this.alumno = alumno;
        this.apoderado = apoderado;
        this.grado = grado;
        this.vacante = vacante;
        this.fechaMatricula = LocalDate.now();
        this.estado = ESTADO_PENDIENTE;
        this.fechaRegistro = LocalDateTime.now();
    }

    public Matricula(Integer idMatricula, Alumno alumno, Apoderado apoderado, Grado grado,
                     Vacante vacante, LocalDate fechaMatricula, String estado, LocalDateTime fechaRegistro) {
        this.idMatricula = idMatricula;
        this.alumno = alumno;
        this.apoderado = apoderado;
        this.grado = grado;
        this.vacante = vacante;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isPendiente() {
        return ESTADO_PENDIENTE.equals(this.estado);
    }

    public boolean isPagado() {
        return ESTADO_PAGADO.equals(this.estado);
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Matrícula #" + idMatricula + " - " + alumno.getNombre() + " (" + estado + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matricula)) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(idMatricula, matricula.idMatricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMatricula);
    }
}
