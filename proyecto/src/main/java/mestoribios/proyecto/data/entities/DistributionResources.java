package mestoribios.proyecto.data.entities;
import java.util.List;

import java.util.ArrayList;

public class DistributionResources
{
    private List<Course>courses;
    private List<Classroom>classrooms;

    DistributionResources(){
        courses = new ArrayList<>();
        classrooms = new ArrayList<>();
    };

    public List<Classroom> getClassrooms() {
        return classrooms;
    }
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    



}