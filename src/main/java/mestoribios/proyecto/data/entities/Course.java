package mestoribios.proyecto.data.entities;

public class Course { 
    private String codCurso;
    private String name;
    private int theoHours = 0;
    private int labHours = 0;
    // private int theoSections;
    // private int labSections;
    private int sections;
    // private int theoCapacity;
    // private int labCapacity;
    private String classroomTheoType = "Aul";
    private String classroomLabType = "Lab";
    private String major;
    private int semester;

    public Course() {
        // Course default constructor
    }

    public Course(String codCurso, String name, int theoHours, int labHours, int sections, int labSections, int theoCapacity,
           int labCapacity, String major, int semester, Boolean tieneAuditorio) {
        this.codCurso = codCurso;
        this.name = name;
        this.theoHours = theoHours;
        this.labHours = labHours;
        this.sections = sections;
        // this.theoSections = theoSections;
        // this.labSections = labSections;
        // this.theoCapacity = theoCapacity;
        // this.labCapacity = labCapacity;
        this.classroomTheoType = tieneAuditorio ? "Aud" : "Aul";
        // this.classroomLabType = "Lab";
        this.major = major;
        this.semester = semester;
    }

    public Course(CourseResponse courseResponse) {
        this.codCurso = courseResponse.codCurso;
        this.name = courseResponse.nomCurso;
        this.sections = courseResponse.secciones;
        this.major = courseResponse.codMalla;
        this.semester = Integer.parseInt(courseResponse.ciclo.split(" ")[1]);
    }


    public String getCodCurso() {
        return codCurso;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public int getSemester() {
        return semester;
    }

    public int getTheoHours() {
        return theoHours;
    }

    public int getLabHours() {
        return labHours;
    }

    public int getSections() {
        return sections;
    }

    // public int getTheoSections() {
    //     return theoSections;
    // }

    // public int getLabSections() {
    //     return labSections;
    // }

    public String getClassroomTheoType() {
        return classroomTheoType;
    }

    public String getClassroomLabType() {
        return classroomLabType;
    }

    // public int getTheoCapacity() {
    //     return theoCapacity;
    // }

    // public int getLabCapacity() {
    //     return labCapacity;
    // }

    // public void setTheoSections(int theoSections) {
    //     this.theoSections = theoSections;
    // }

    // public void setLabSections(int labSections) {
    //     this.labSections = labSections;
    // }
    public void setSections(int sections) {
        this.sections = sections;
    }

    public void increaseTheoHours(int theoHours) {
        this.theoHours += theoHours;
    }

    public void increaseLabHours(int labHours) {
        this.labHours += labHours;
    }

    public void setSemester(String ciclo) {
        this.semester = Integer.parseInt(ciclo.split(" ")[1]);
    }
}