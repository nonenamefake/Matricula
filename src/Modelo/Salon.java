package Modelo;
import java.io.Serializable;

public class Salon implements Serializable {
    private int id_salon;
    private String codigo;
    private String nombre;
    private int capacidad;
    private int id_grado;

    public int getId_salon() { return id_salon; }
    public void setId_salon(int id_salon) { this.id_salon = id_salon; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public int getId_grado() { return id_grado; }
    public void setId_grado(int id_grado) { this.id_grado = id_grado; }

    public Salon() {}

    public Object[] Registro() {
        Object[] fila = {id_salon, codigo, nombre, capacidad, id_grado};
        return fila;
    }
}
