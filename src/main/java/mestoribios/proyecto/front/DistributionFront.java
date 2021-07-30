package mestoribios.proyecto.front;

import java.util.List;

public class DistributionFront {
    Integer classroomExistButNotUsed;
    List<ClassroomFront> classroomUsed;
    List<ClassroomFront> classroomMissing;

    public DistributionFront(Integer classroomExistButNotUsed, List<ClassroomFront> classroomUsed, List<ClassroomFront> classroomMissing) {
        this.classroomExistButNotUsed = classroomExistButNotUsed;
        this.classroomUsed = classroomUsed;
        this.classroomMissing = classroomMissing;
    }
}