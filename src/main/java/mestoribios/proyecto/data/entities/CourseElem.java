package mestoribios.proyecto.data.entities;

public class CourseElem {
    String codCurso;
    String name;
    String major;
    int semester;
    int section;

    CourseElem(String codCurso, String name, String major, int semester, int section){
        this.codCurso = codCurso;
        this.name = name;
        this.major = major;
        this.semester = semester;
        this.section = section;
    }
}
