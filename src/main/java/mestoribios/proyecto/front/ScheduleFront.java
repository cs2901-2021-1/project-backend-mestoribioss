package mestoribios.proyecto.front;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFront {
    String day;
    Integer dayNumber;
    List<CourseFront> courses;

    public ScheduleFront() {
        // default
        courses = new ArrayList<>();
    }

    public void setDayValues(int dayVal) {
        dayNumber = dayVal+1;

        if (dayNumber == 1) {
            day = "Lunes";
        }
        else if (dayNumber == 2) {
            day = "Martes";
        }
        else if (dayNumber == 3) {
            day = "Miércoles";
        }
        else if (dayNumber == 4) {
            day = "Jueves";
        }
        else if (dayNumber == 5) {
            day = "Viernes";
        }
        else if (dayNumber == 6) {
            day = "Sábado";
        }
    }

    public void addCourseFront(CourseFront course) {
        courses.add(course);
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public List<CourseFront> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseFront> courses) {
        this.courses = courses;
    }

    
}
