package mestoribios.proyecto.data.entities;

import java.util.ArrayList;
import java.util.List;

import mestoribios.proyecto.front.CourseFront;
import mestoribios.proyecto.front.ScheduleFront;

public class Classroom {
    // private Integer id;
    private String name;
    private String type;
    private Integer capacity;
    private boolean used = false;
    private boolean exist = true;
    private CourseElem[][] timeSchedule;

    private void fillTimeSchedule() {
        timeSchedule = new CourseElem[15][6];
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 6; ++j) {
                timeSchedule[i][j] = new CourseElem("none", "none", "none", -1, 0);
            }
        }
    }

    public Classroom(){
        fillTimeSchedule();
    }

    public Classroom(String name, String type, int capacity){
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        fillTimeSchedule();
    }

    public Classroom(ClassroomResponse classroomResponse){
        this.name = classroomResponse.getCodAula();
        this.type = classroomResponse.getTipo();
        this.capacity = classroomResponse.getCapacity();
        fillTimeSchedule();
    }

    void fillHour(Course course, int hour, int day, int i) {
        CourseElem c = new CourseElem(course.getCodCurso(), course.getName(), course.getMajor(), course.getSemester(), i);
        timeSchedule[hour][day] = c;
    }

    boolean checkAvailability(int i, int j) {
        return timeSchedule[i][j].name.equals("none") && timeSchedule[i][j].major.equals("none") &&
               timeSchedule[i][j].semester == -1 && timeSchedule[i][j].section == 0;
    }

    public String getType(){
        return type;
    }

    public void setUsed(){
        this.used = true;
    }

    public void setExist(){
        this.exist = false;
    }

    public boolean getUsed() {
        return this.used;
    }

    public String getName() {
        return this.name;
    }

    public boolean getExist() {
        return this.exist;
    }
    
    String printTimeSchedule() {
        StringBuilder strTimeSchedule = new StringBuilder(name + "\n");
        for (int i = 0; i < 15; ++i) {
            strTimeSchedule.append(Integer.toString(i + 7)).append(":00");
            for (int j = 0; j < 6; ++j)
                strTimeSchedule.append("\t").append(timeSchedule[i][j].name).append(",").append(Integer.toString(timeSchedule[i][j].section)).append(",").append(timeSchedule[i][j].major).append("\t");
            strTimeSchedule.append("\n");
        }
        return strTimeSchedule.toString();
    }

    public List<ScheduleFront> getScheduleFront() {
        List<ScheduleFront> lista = new ArrayList<>();
        // días
        for (int i = 0; i < 6; ++i) {
            ScheduleFront scheduleFront = new ScheduleFront();
            scheduleFront.setDayValues(i);
            // hora
            for (int j = 0; j < 15; ++j) {
                CourseFront courseFront = new CourseFront(
                    timeSchedule[j][i].codCurso,
                    timeSchedule[j][i].name,
                    timeSchedule[j][i].major,
                    timeSchedule[j][i].semester,
                    timeSchedule[j][i].section,
                    j+7,
                    j+8);
                scheduleFront.addCourseFront(courseFront);
            }
            lista.add(scheduleFront);
        }
        return lista;
    }
}