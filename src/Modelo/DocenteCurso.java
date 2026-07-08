package Modelo;
import java.io.Serializable;

public class DocenteCurso implements Serializable {
    private int id_docente_curso;
    private int id_docente;
    private int id_grado_curso;
    private int id_anio;

    public int getId_docente_curso() { return id_docente_curso; }
    public void setId_docente_curso(int id) { this.id_docente_curso = id; }
    public int getId_docente() { return id_docente; }
    public void setId_docente(int id) { this.id_docente = id; }
    public int getId_grado_curso() { return id_grado_curso; }
    public void setId_grado_curso(int id) { this.id_grado_curso = id; }
    public int getId_anio() { return id_anio; }
    public void setId_anio(int id) { this.id_anio = id; }

    public DocenteCurso() {}

    public Object[] Registro() {
        Object[] fila = {id_docente_curso, id_docente, id_grado_curso, id_anio};
        return fila;
    }
}
