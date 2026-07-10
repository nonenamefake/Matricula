package Modelo;
import java.io.Serializable;

public class Matricula implements Serializable {
    private int id_matricula;
    private int id_estudiante;
    private int id_anio;
    private int id_grado;
    private int id_salon;
    private String fecha_matricula;

    public int getId_matricula() { return id_matricula; }
    public void setId_matricula(int id_matricula) { this.id_matricula = id_matricula; }
    public int getId_estudiante() { return id_estudiante; }
    public void setId_estudiante(int id_estudiante) { this.id_estudiante = id_estudiante; }
    public int getId_anio() { return id_anio; }
    public void setId_anio(int id_anio) { this.id_anio = id_anio; }
    public int getId_grado() { return id_grado; }
    public void setId_grado(int id_grado) { this.id_grado = id_grado; }
    public int getId_salon() { return id_salon; }
    public void setId_salon(int id_salon) { this.id_salon = id_salon; }
    public String getFecha_matricula() { return fecha_matricula; }
    public void setFecha_matricula(String fecha_matricula) { this.fecha_matricula = fecha_matricula; }

    public Matricula() {}
}
