package mestoribios.proyecto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mestoribios.proyecto.data.entities.Classroom;
import mestoribios.proyecto.data.entities.ClassroomResponse;
import mestoribios.proyecto.data.entities.Course;
import mestoribios.proyecto.data.entities.CourseResponse;
import mestoribios.proyecto.data.entities.Distribution;
import mestoribios.proyecto.front.DistributionFront;
import mestoribios.proyecto.service.QueryService;
import mestoribios.proyecto.service.ResponseService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/distribution")
@CrossOrigin
public class DistributionController {
    @GetMapping
    @RequestMapping("/generate")
    public ResponseEntity<DistributionFront> generateDistribution() throws SQLException {
        HashMap<String, Object> map = new HashMap<>();
        try {
            QueryService queryService = new QueryService();
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
                else if (!courseResponse.getCodMalla().equals(lastMalla) && !lastMalla.equals("")) {
                    // curso.setSemester(courseResponse.getCiclo());
                    if (!utecCourses.containsKey(lastMalla)) utecCourses.put(new String(lastMalla), new ArrayList<>());
                    listAux = utecCourses.get(lastMalla);
                    if (!listAux.contains(curso))
                    {
                        listAux.add(curso);
                        utecCourses.put(courseResponse.getCodMalla(), listAux);
                        curso.setSemester(courseResponse.getCiclo());
                    }
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
            distribution.generateDistribution(); // index out of range
            DistributionFront distributionFront = distribution.getDetailedDistributionFront(); // index out of range
            map.put("data", distributionFront);
            map.put("message", "Distribución generada!");
            return new ResponseEntity<>(distributionFront, HttpStatus.OK);
            // return new ResponseEntity<HashMap<String, DistributionFront>, HttpStatus>(map, HttpStatus.OK);
        }
        catch (SQLException e) {
            e.printStackTrace();
            map.put("error", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            // return new ResponseEntity<HashMap<String, DistributionFront>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        /*
        descomentar cuando ya este arreglado lo de los cursos (da error porq no hay cursos)
        no borrar esto
        Distribution distribution = new Distribution();
        ArrayList <Pair<Integer, String>> lista = new ArrayList<>();
        distributionDTO.forEach(dist -> {
            lista.add(new Pair<>(Integer.parseInt(dist.get("numberStudents")), dist.get("major")));
        });
        distribution.setNumberStudents(lista);
        distribution.generateDistribution();
        return new ResponseEntity(distribution.showDistributionFront(), HttpStatus.OK);
         */
    }

}
