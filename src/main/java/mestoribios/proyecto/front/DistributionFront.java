package mestoribios.proyecto.front;

import java.util.List;

// import javax.transaction.Transactional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import mestoribios.proyecto.data.repositories.UserRepository;

// @Service
// @Transactional
public class DistributionFront {
    Integer classroomExistButNotUsed;
    List<ClassroomFront> classroomUsed;
    List<ClassroomFront> classroomMissing;

    public DistributionFront(Integer classroomExistButNotUsed, List<ClassroomFront> classroomUsed, List<ClassroomFront> classroomMissing) {
        this.classroomExistButNotUsed = classroomExistButNotUsed;
        this.classroomUsed = classroomUsed;
        this.classroomMissing = classroomMissing;
    }

    public Integer getClassroomExistButNotUsed() {
        return classroomExistButNotUsed;
    }

    public void setClassroomExistButNotUsed(Integer classroomExistButNotUsed) {
        this.classroomExistButNotUsed = classroomExistButNotUsed;
    }

    public List<ClassroomFront> getClassroomUsed() {
        return classroomUsed;
    }

    public void setClassroomUsed(List<ClassroomFront> classroomUsed) {
        this.classroomUsed = classroomUsed;
    }

    public List<ClassroomFront> getClassroomMissing() {
        return classroomMissing;
    }

    public void setClassroomMissing(List<ClassroomFront> classroomMissing) {
        this.classroomMissing = classroomMissing;
    }
}