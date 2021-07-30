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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    
}
