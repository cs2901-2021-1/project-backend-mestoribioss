package mestoribios.proyecto.front;

// import java.util.ArrayList;
import java.util.List;

import mestoribios.proyecto.data.entities.Classroom;

public class ClassroomFront {
    String name;
    String type;
    List<ScheduleFront> schedule;

    public ClassroomFront(Classroom classroom) {
        // schedule = new ArrayList<>();
        this.name = classroom.getName();
        this.type = classroom.getType();
        schedule = classroom.getScheduleFront();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ScheduleFront> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleFront> schedule) {
        this.schedule = schedule;
    }
}
