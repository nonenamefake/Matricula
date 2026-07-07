package Modelo;
import java.io.Serializable;

public class Curso implements Serializable {
    private int id_curso;
    private String codigo;
    private String nombre;
    private int horas_semanales;

    public int getId_curso() { return id_curso; }
    public void setId_curso(int id_curso) { this.id_curso = id_curso; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getHoras_semanales() { return horas_semanales; }
    public void setHoras_semanales(int horas_semanales) { this.horas_semanales = horas_semanales; }

    public Curso() {}

    public Object[] Registro() {
        Object[] fila = {id_curso, codigo, nombre, horas_semanales};
        return fila;
    }
}
