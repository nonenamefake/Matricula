package Modelo;
import java.io.Serializable;

public class Docentes implements Serializable {
    private int id_docente;
    private String dni;
    private String nombres;
    private String apellido_pa;
    private String apellido_ma;
    private String especialidad;
    private String telefono;
    private String correo;
    private String estado;

    public int getId_docente() { return id_docente; }
    public void setId_docente(int id_docente) { this.id_docente = id_docente; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellido_pa() { return apellido_pa; }
    public void setApellido_pa(String apellido_pa) { this.apellido_pa = apellido_pa; }
    public String getApellido_ma() { return apellido_ma; }
    public void setApellido_ma(String apellido_ma) { this.apellido_ma = apellido_ma; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Docentes() {}

    public Object[] Registro() {
        Object[] fila = {id_docente, dni, nombres, apellido_pa, apellido_ma, especialidad, telefono, correo};
        return fila;
    }
}
