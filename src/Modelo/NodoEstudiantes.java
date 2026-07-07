
package Modelo;

import java.io.Serializable;

public class NodoEstudiantes implements Serializable{
    public Estudiantes es;
  public NodoEstudiantes sig;
  public NodoEstudiantes ant;
  public NodoEstudiantes(Estudiantes e){
      this.es=e;
      ant=sig=null;
  }
}
