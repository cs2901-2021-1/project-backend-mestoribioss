package mestoribios.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mestoribios.proyecto.data.entities.Classroom;
import mestoribios.proyecto.data.entities.ClassroomResponse;
import mestoribios.proyecto.data.entities.Course;
import mestoribios.proyecto.data.entities.CourseResponse;
import mestoribios.proyecto.data.entities.Distribution;
import mestoribios.proyecto.data.entities.SystemParameters;
import mestoribios.proyecto.front.DistributionFront;
import mestoribios.proyecto.service.QueryService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/distribution")
@CrossOrigin
public class DistributionController {

    
    @Autowired
    QueryService queryService;


    @PostMapping
    public ResponseEntity<DistributionFront> generateDistributionCachimbos(@RequestBody(required = false) SystemParameters ingresantes) throws SQLException {
        HashMap<String, Object> map = new HashMap<>();
        try {
            // QueryService queryService = new QueryService();
            
            return new ResponseEntity<>(makeDistribution(ingresantes), HttpStatus.OK);
        }
        catch (SQLException e) {
            e.printStackTrace();
            map.put("error", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public DistributionFront makeDistribution(SystemParameters ingresantes) throws SQLException{

        List<CourseResponse> courses = queryService.executeQueryCourses();
        List<ClassroomResponse> classroom = queryService.executeQueryClassrooms();
        Map<String, List<Course>> utecCourses = new HashMap<>();
        Course curso = new Course();
        String lastNombre = "";
        String lastMalla = "";
        List<Classroom> totalClassrooms = new ArrayList<>();
        List<Course> listAux;

        for (CourseResponse courseResponse : courses) {
            listAux = new ArrayList<>();
            if (!courseResponse.getNomCurso().equals(lastNombre)) {
                if (!lastNombre.equals("")) {
                    if (!utecCourses.containsKey(lastMalla)) utecCourses.put(new String(lastMalla), new ArrayList<>());
                    listAux = utecCourses.get(lastMalla);
                    listAux.add(curso);
                    utecCourses.put(lastMalla, listAux);
                }
                curso = new Course(courseResponse);
            }

            if (courseResponse.getType().equals("Teoría")) {
                curso.increaseTheoHours(courseResponse.getHorasSemanales());
            }
            else if (courseResponse.getType().equals("Laboratorio")) {
                curso.increaseLabHours(courseResponse.getHorasSemanales());                    
            }
            lastNombre = courseResponse.getNomCurso();
            lastMalla  = courseResponse.getCodMalla();
        }
        
        for (ClassroomResponse classroomResponse : classroom) {
            totalClassrooms.add(new Classroom(classroomResponse));
        }

        Distribution distribution = new Distribution(utecCourses, totalClassrooms);
        if(ingresantes.getListIngresenates()!=null){
            distribution.setNumberStudents(ingresantes);
        }
        distribution.generateDistribution();
        DistributionFront distributionFront = distribution.getDetailedDistributionFront();
        return distributionFront;
    }

}


