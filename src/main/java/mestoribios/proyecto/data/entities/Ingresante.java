package mestoribios.proyecto.data.entities;

public class Ingresante {
    String major;
    Integer numberStudents;
    
    public Ingresante(String major, Integer numberStudents) {
        this.major = major;
        this.numberStudents = numberStudents;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(Integer numberStudents) {
        this.numberStudents = numberStudents;
    }
}
