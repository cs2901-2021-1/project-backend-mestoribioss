package data.entities;
import java.util.HashMap;
import java.util.Map;

public class Course {

    String nombre;
    int horasTeo;
    int horasLab;
    int secciones;
    String classroomTeoType;
    String classroomLabType;
    //int = ciclo
    Map<String, Integer> majorCourses;

    Course(){
        this.majorCourses = new HashMap<>();
    };

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorasTeo(int horas) {
        this.horasTeo = horas;
    }

    public void setHorasLab(int horas) {
        this.horasLab = horas;
    }

    public void setSecciones(int secciones) {
        this.secciones = secciones;
    }

    public void setClassroomTeoType(String classroomtype) {
        this.classroomTeoType = classroomtype;
    }

    public void setClassroomLabType(String classroomtype) {
        this.classroomLabType = classroomtype;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getHorasTeo() {
        return this.horasTeo;
    }

    public int getHorasLab() {
        return this.horasLab;
    }

    public int getSecciones() {
        return this.secciones;
    }

    String setClassroomTeoType() {
        return this.classroomTeoType;
    }

    String setClassroomLabType() {
        return this.classroomLabType;
    }

}