package Modelo;
import java.io.Serializable;

public class Horario implements Serializable {
    private int id_horario;
    private int id_docente_curso;
    private int id_salon;
    private String dia_semana;
    private String hora_inicio;
    private String hora_fin;

    public int getId_horario() { return id_horario; }
    public void setId_horario(int id) { this.id_horario = id; }
    public int getId_docente_curso() { return id_docente_curso; }
    public void setId_docente_curso(int id) { this.id_docente_curso = id; }
    public int getId_salon() { return id_salon; }
    public void setId_salon(int id) { this.id_salon = id; }
    public String getDia_semana() { return dia_semana; }
    public void setDia_semana(String d) { this.dia_semana = d; }
    public String getHora_inicio() { return hora_inicio; }
    public void setHora_inicio(String h) { this.hora_inicio = h; }
    public String getHora_fin() { return hora_fin; }
    public void setHora_fin(String h) { this.hora_fin = h; }

    public Horario() {}

    public Object[] Registro() {
        Object[] fila = {id_horario, id_docente_curso, id_salon, dia_semana, hora_inicio, hora_fin};
        return fila;
    }
}
