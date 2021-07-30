package mestoribios.proyecto.front;

public class CourseFront {
    String code;
    String name;
    String major;
    Integer semester;
    Integer section;
    Integer startHour;
    Integer endHour;

    public CourseFront(String code, String name, String major, Integer semester, 
    Integer section, Integer startHour, Integer endHour) {
        this.code = code;
        this.name = name;
        this.major = major;
        this.semester = semester;
        this.section = section;
        this.startHour = startHour;
        this.endHour = endHour;
    }
}
