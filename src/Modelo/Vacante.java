package Modelo;

import java.util.Objects;

public class Vacante {

    public static final String ESTADO_ACTIVO = "activo";
    public static final String ESTADO_CERRADO = "cerrado";
    public static final String ESTADO_FINALIZADO = "finalizado";

    private Integer idVacante;
    private Grado grado;
    private String seccion;
    private int cantidad;
    private String estado;

    public Vacante() {
    }

    public Vacante(Grado grado, String seccion, int cantidad) {
        this.grado = grado;
        this.seccion = seccion;
        this.cantidad = cantidad;
        this.estado = ESTADO_ACTIVO;
    }

    public Vacante(Integer idVacante, Grado grado, String seccion, int cantidad, String estado) {
        this.idVacante = idVacante;
        this.grado = grado;
        this.seccion = seccion;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isActivo() {
        return ESTADO_ACTIVO.equals(this.estado);
    }

    public boolean isCerrado() {
        return ESTADO_CERRADO.equals(this.estado);
    }

    public boolean isFinalizado() {
        return ESTADO_FINALIZADO.equals(this.estado);
    }

    @Override
    public String toString() {
        return grado.getNombre() + " - Sección " + seccion + " (" + estado + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacante)) return false;
        Vacante vacante = (Vacante) o;
        return Objects.equals(idVacante, vacante.idVacante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVacante);
    }
}
