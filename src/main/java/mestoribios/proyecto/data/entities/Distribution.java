package mestoribios.proyecto.data.entities;

import java.util.List;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import mestoribios.proyecto.config.Pair;
import mestoribios.proyecto.front.ClassroomFront;
import mestoribios.proyecto.front.DistributionFront;

public class Distribution {
    private Map<String, List<Course>> courses;
    private List<Classroom> totalClassrooms;
    private Map<String, Integer> existingClassroomsNeeded;
    private Map<String, Integer> notExistingClassroomsNeeded;
    private List<Classroom> classroomsNeeded;
    private Map<Float, ArrayList<DistributionElem>> crossingOfSchedules;
    private String nameForNewClassrooms = "NEW";
    private int idForNewClassrooms;
    private boolean modify = false;
    static final Logger logger = Logger.getLogger(Distribution.class.getName());

    private void reset() {
        idForNewClassrooms = 0;
        crossingOfSchedules = new HashMap<>();
        classroomsNeeded = new ArrayList<>();
        existingClassroomsNeeded = new HashMap<>();
        notExistingClassroomsNeeded = new HashMap<>();
    }

    public Distribution() {
        // Distribution default constructor
        courses = new HashMap<>();
        totalClassrooms  = new ArrayList<>();
        reset();
    }

    public Distribution(Map<String, List<Course>> courses, List<Classroom> totalClassrooms) {
        this.courses = courses;
        this.totalClassrooms = totalClassrooms;
        reset();
    }

    public void updateClassroom(Classroom a){
        for (int i = 0; i < totalClassrooms.size(); ++i){
            if (totalClassrooms.get(i).getName().equals(a.getName())){
                totalClassrooms.set(i, a);
            }
        }
    }

    public void allocateInMatrix(Classroom a, Course b, int hours, int beginTime, int day, int section) {
        for (int i = beginTime; i < beginTime + hours; ++i) {
            a.fillHour(b, i, day, section);
            updateClassroom(a);
        }
        float key = beginTime + day * 0.01f;
        Map<Integer, Boolean> crosses = new HashMap<>();
        crosses.put(section, true);
        DistributionElem elem = new DistributionElem(b.getMajor(), b.getSemester(), crosses);
        if (crossingOfSchedules.containsKey(key)) {
            crossingOfSchedules.get(key).add(elem);
        }
        else {
            ArrayList<DistributionElem> vec = new ArrayList<>();
            vec.add(elem);
            crossingOfSchedules.put(key, vec);
        }
    }

    public static boolean checkTypeOfClassroom(boolean theo, Classroom c, Course a) {
        if (theo) return c.getType().equals(a.getClassroomTheoType());
        else return c.getType().equals(a.getClassroomLabType());
    }

    public static boolean notInDays(int j, List<Integer> days) {
        for (int e : days) {
            if (e == j) return false;
        }
        return true;
    }

    public boolean checkCrossing(int i, int j, int section, Course a) {
        float key = i + j * 0.01f;
        if (!crossingOfSchedules.containsKey(key)) return true;
        ArrayList<DistributionElem> vec = crossingOfSchedules.get(key);
        for (DistributionElem v: vec) {
            if (v.semester == a.getSemester() && v.major.equals(a.getMajor()) && v.crosses.containsKey(section)) {
                return false;
            }
        }
        return true;
    }

    public Pair<Classroom, Pair<Integer, Integer>>
    makeMyPair(Course a, int hours, List<Integer> days, int n,
               int m, boolean theo, Classroom c, int section) {
        Pair<Classroom, Pair<Integer, Integer>> available = new Pair<>(new Classroom(), new Pair<>());
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                var avail = c.checkAvailability(i, j);
                var notdays = notInDays(j, days);
                var typeclass = checkTypeOfClassroom(theo, c, a);
                var crossing = checkCrossing(i, j, section, a);
                if (avail && notdays && typeclass && crossing) {
                    available.setFirst(c);
                    available.getSecond().setFirst(i);
                    available.getSecond().setSecond(j);
                    if (hours == 1 || c.checkAvailability(i + 1, j)) return available;
                }
            }
        }
        available.setFirst(c);
        available.getSecond().setFirst(-2);
        available.getSecond().setSecond(-2);
        return available;
    }

    public Pair<Classroom, Pair<Integer, Integer>>
    function(Course a, int hours, List<Integer> days, int n,
             int m, boolean theo, int section) {
        Pair<Classroom, Pair<Integer, Integer>> available = new Pair<>();
        for (Classroom c : totalClassrooms) {
            available = makeMyPair(a, hours, days, n, m, theo, c, section);
            if (available.getSecond().getFirst() != -2 && available.getSecond().getSecond() != -2) break;
        }

        if (available.getSecond().getFirst() != -2 && available.getSecond().getSecond() != -2) return available;
        String type;
        if (theo) type = a.getClassroomTheoType();
        else type = a.getClassroomLabType();
        Classroom newClassroom = new Classroom(nameForNewClassrooms + Integer.toString(idForNewClassrooms), type, 45);
        ++idForNewClassrooms;
        newClassroom.setExist();
        newClassroom.setUsed();
        totalClassrooms.add(newClassroom);
        available = makeMyPair(a, hours, days, n, m, theo, newClassroom, section);
        return available;
    }

    public Pair<Classroom, Pair<Integer, Integer>>
    checkForClassroomAvailable(Course a, int hours, List<Integer> days, boolean theo, int section) {
        Pair<Classroom, Pair<Integer, Integer>> available;
        boolean firstYear;
        if (a.getSemester() <= 2) firstYear = true;
        else firstYear = false;
        if (firstYear)
            available = function(a, hours, days, 10, 6, theo, section);
        else
            available = function(a, hours, days, 14, 6, theo, section);
        return available;
    }

    public void allocateCourse(Course a) {
        int i;
        int j;
        int classesTheo;
        int classesLab;
        int hours;
        Pair<Classroom, Pair<Integer, Integer>> b;

        for (i = 0; i < a.getSections(); ++i) {
            if (a.getTheoHours() % 2 == 1) classesTheo = (a.getTheoHours() / 2) + 1;
            else classesTheo = a.getTheoHours()/2;
            for (j = 0; j < classesTheo; ++j) {
                ArrayList<Integer> days = new ArrayList<>();
                if (j < a.getTheoHours() / 2) hours = 2;
                else hours = 1;
                b = checkForClassroomAvailable(a, hours, days, true, i+1);
                days.add(b.getSecond().getSecond());
                b.getFirst().setUsed();
                updateClassroom(b.getFirst());
                allocateInMatrix(b.getFirst(), a, hours, b.getSecond().getFirst(), b.getSecond().getSecond(), i+1);
            }

            if (a.getLabHours() % 2 == 1) classesLab = a.getLabHours() / 2 + 1;
            else classesLab = a.getLabHours()/2;
            for (j = 0; j < classesLab; ++j) {
                ArrayList<Integer> days = new ArrayList<>();
                if (j < a.getLabHours() / 2) hours = 2;
                else hours = 1;
                b = checkForClassroomAvailable(a, hours, days, false, i+1);
                days.add(b.getSecond().getSecond());
                b.getFirst().setUsed();
                updateClassroom(b.getFirst());
                allocateInMatrix(b.getFirst(), a, hours, b.getSecond().getFirst(), b.getSecond().getSecond(), i+1);
            }
        }
    }

    private void existingClassroomQuery(Classroom classroom){
        if (existingClassroomsNeeded.containsKey(classroom.getType()))
            existingClassroomsNeeded.put(classroom.getType(), existingClassroomsNeeded.get(classroom.getType())+1);
        else
            existingClassroomsNeeded.put(classroom.getType(), 1);
    }

    private void notExistingClassroomQuery(Classroom classroom){
        if (notExistingClassroomsNeeded.containsKey(classroom.getType()))
            notExistingClassroomsNeeded.put(classroom.getType(), notExistingClassroomsNeeded.get(classroom.getType())+1);
        else
            notExistingClassroomsNeeded.put(classroom.getType(), 1);
    }

    public void generateDistribution() {
        if (modify) return;
        modify = true;
        for (List<Course> course : courses.values())
            for (Course value : course) allocateCourse(value);
        for (Classroom classroom : totalClassrooms) {
            if (classroom.getUsed()) {
                classroomsNeeded.add(classroom);
                if (classroom.getExist())
                    existingClassroomQuery(classroom);
                else
                    notExistingClassroomQuery(classroom);
            }            
        }
    }

    public void showDistribution() {
        logger.info("Existing Classrooms");
        for (Map.Entry<String, Integer> entry : existingClassroomsNeeded.entrySet()) {
            logger.info(entry.getKey() + ": " + entry.getValue());
        }
        logger.info("Non-Existing Classrooms");
        for (Map.Entry<String, Integer> entry : notExistingClassroomsNeeded.entrySet()) {
            logger.info(entry.getKey() + ": " + entry.getValue());
        }
    }

    public List<HashMap<String, Integer>> showDistributionFront() {
        HashMap<String, Integer> existentes = new HashMap<>();
        HashMap<String, Integer> noExistentes = new HashMap<>();
        for (Map.Entry<String, Integer> entry : existingClassroomsNeeded.entrySet())
            existentes.put(entry.getKey(), entry.getValue());
        for (Map.Entry<String, Integer> entry : notExistingClassroomsNeeded.entrySet())
            noExistentes.put(entry.getKey(), entry.getValue());

        List<HashMap<String, Integer>> res = new ArrayList<>();

        res.add(existentes);
        res.add(noExistentes);

        return res;
    }

    public void showDetailedDistribution() {
        for (Classroom classroom : classroomsNeeded) {
            classroom.printTimeSchedule();
        }
    }

    public DistributionFront getDetailedDistributionFront() {
        List<ClassroomFront> classroomsUsed = new ArrayList<>();
        List<ClassroomFront> classroomsMissing = new ArrayList<>();

        for (Classroom classroom : classroomsNeeded) {
            if (classroom.getExist())
            {
                classroomsUsed.add(new ClassroomFront(classroom));
            }
            else
            {
                classroomsMissing.add(new ClassroomFront(classroom));
            }
        }
        DistributionFront distributionFront = new DistributionFront(totalClassrooms.size() - classroomsNeeded.size(), classroomsUsed, classroomsMissing);
        return distributionFront;
    }

    // public void setNumberStudents(List<Pair<Integer, String>> numberStudents) {
    //     for (Pair<Integer,String> pair : numberStudents) {
    //         List<Course> coursesFromMajor = courses.get(pair.getSecond());
    //         for (Course courseFromMajor : coursesFromMajor) {
    //             if (courseFromMajor.getSemester() == 1) {
    //                 float num = pair.getFirst();
    //                 int soloAula = courseFromMajor.getSections() + (int) (Math.ceil(num/45));
    //                 if (courseFromMajor.getClassroomTheoType().equals("Aul"))
    //                     courseFromMajor.setSections(soloAula);
    //                 else if (courseFromMajor.getClassroomTheoType().equals("Aud"))
    //                     courseFromMajor.setSections(soloAula + (int) (Math.floor(num/306)));
    //             }
    //         }
    //     }
    //     modify = false;
    //     reset();
    // }

      public void setNumberStudents(SystemParameters numberStudents) {
        for (Ingresante pair : numberStudents.getListIngresenates()) {
            List<Course> coursesFromMajor = courses.get(pair.getMajor());
            for (Course courseFromMajor : coursesFromMajor) {
                if (courseFromMajor.getSemester() == 1) {
                    float num = pair.getNumberStudents();
                    int soloAula = courseFromMajor.getSections() + (int) (Math.ceil(num/45));
                    if (courseFromMajor.getClassroomTheoType().equals("Aul"))
                        courseFromMajor.setSections(soloAula);
                    else if (courseFromMajor.getClassroomTheoType().equals("Aud"))
                        courseFromMajor.setSections(soloAula + (int) (Math.floor(num/306)));
                }
            }
        }
        modify = false;
        reset();
    }

    public int getNotExistingClassroomsNeeded() {
        logger.info(Integer.toString(notExistingClassroomsNeeded.size()));
        return notExistingClassroomsNeeded.size();
    }

    public List<Classroom> getClassroomsNeeded() {
        return classroomsNeeded;
    }
}