package Modelo;
import java.io.Serializable;
import java.util.Date;

public class AnioAcademico implements Serializable {
    private int id_anio;
    private String anio;
    private Date fecha_inicio;
    private Date fecha_fin;
    private String activo;

    public int getId_anio() { return id_anio; }
    public void setId_anio(int id_anio) { this.id_anio = id_anio; }
    public String getAnio() { return anio; }
    public void setAnio(String anio) { this.anio = anio; }
    public Date getFecha_inicio() { return fecha_inicio; }
    public void setFecha_inicio(Date fecha_inicio) { this.fecha_inicio = fecha_inicio; }
    public Date getFecha_fin() { return fecha_fin; }
    public void setFecha_fin(Date fecha_fin) { this.fecha_fin = fecha_fin; }
    public String getActivo() { return activo; }
    public void setActivo(String activo) { this.activo = activo; }

    public AnioAcademico() {}

    public Object[] Registro() {
        Object[] fila = {id_anio, anio, fecha_inicio, fecha_fin, activo};
        return fila;
    }
}
