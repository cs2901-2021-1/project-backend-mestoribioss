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
}
