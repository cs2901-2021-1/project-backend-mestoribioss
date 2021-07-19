package mestoribios.proyecto.data.entities;

public class Classroom {
    private Integer id;
    private String name;
    private String type;
    private boolean used = false;
    private boolean exist = true;
    private CourseElem[][] timeSchedule;

    private void fillTimeSchedule() {
        timeSchedule = new CourseElem[15][6];
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 6; ++j) {
                timeSchedule[i][j] = new CourseElem("none", "none", -1, 0);
            }
        }
    }

    public Classroom(){
        fillTimeSchedule();
    }

    public Classroom(int id, String name, String type){
        this.id = id;
        this.name = name;
        this.type = type;
        fillTimeSchedule();
    }

    void fillHour(Course course, int hour, int day, int i) {
        CourseElem c = new CourseElem(course.getName(), course.getMajor(), course.getSemester(), i);
        timeSchedule[hour][day] = c;
    }

    boolean checkAvailability(int i, int j) {
        return timeSchedule[i][j].name.equals("none") && timeSchedule[i][j].major.equals("none") &&
               timeSchedule[i][j].semester == -1 && timeSchedule[i][j].section == 0;
    }

    String getType(){
        return type;
    }

    void setUsed(){
        this.used = true;
    }

    void setExist(){
        this.exist = false;
    }

    int getId(){
        return this.id;
    }

    boolean getUsed() {
        return this.used;
    }

    boolean getExist() {
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
}