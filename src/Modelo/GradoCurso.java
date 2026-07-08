package Modelo;
import java.io.Serializable;

public class GradoCurso implements Serializable {
    private int id_grado_curso;
    private int id_grado;
    private int id_curso;

    public int getId_grado_curso() { return id_grado_curso; }
    public void setId_grado_curso(int id) { this.id_grado_curso = id; }
    public int getId_grado() { return id_grado; }
    public void setId_grado(int id) { this.id_grado = id; }
    public int getId_curso() { return id_curso; }
    public void setId_curso(int id) { this.id_curso = id; }

    public GradoCurso() {}

    public Object[] Registro() {
        Object[] fila = {id_grado_curso, id_grado, id_curso};
        return fila;
    }

    public Object[] RegistroConNombres(String nombreGrado, String nombreCurso) {
        Object[] fila = {id_grado_curso, nombreGrado, nombreCurso};
        return fila;
    }

    public static String nombreGrado(int id) {
        String[] nombres = {"", "Primer Grado", "Segundo Grado", "Tercer Grado",
                            "Cuarto Grado", "Quinto Grado", "Sexto Grado"};
        if (id >= 1 && id <= 6) return nombres[id];
        return "Desconocido";
    }
}
