package mestoribios.proyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import mestoribios.proyecto.config.Pair;
import mestoribios.proyecto.data.entities.Classroom;
import mestoribios.proyecto.data.entities.Course;
import mestoribios.proyecto.data.entities.Distribution;

@SpringBootTest
class ProyectoApplicationTests {

	@Test
	void test1() {
		Course c1 = new Course("Ingenieria de Software 1", 2, 4, 2, 2, 45, 45, "comun", "comun", "CS", 1);
		Course c2 = new Course("Base de Datos 2", 1, 4, 1, 1, 45, 45, "comun", "comun", "CS", 1);
		Course c3 = new Course("Sistemas Operativos", 2, 4, 1, 1, 45, 45, "comun", "comun", "CS", 1);
		Course c4 = new Course("Compiladores", 2, 4, 1, 1, 45, 45, "comun", "comun", "CS", 1);
		Course c5 = new Course("Analisis y Diseno de Algoritmos", 2, 4, 2, 2, 45, 45, "comun", "comun", "CS", 1);
		Classroom cl1 = new Classroom(601, "A601", "comun");
		Map<String, List<Course>> utecCourses = new HashMap<>();
		List<Course> csCourses = new ArrayList<>();
		List<Classroom> totalClassrooms = new ArrayList<>();
		csCourses.add(c1);
		csCourses.add(c2);
		csCourses.add(c3);
		csCourses.add(c4);
		csCourses.add(c5);
		totalClassrooms.add(cl1);
		utecCourses.put("CS", csCourses);
		Distribution distribution = new Distribution(utecCourses, totalClassrooms);
		distribution.generateDistribution();
		Assert.assertEquals(distribution.getNotExistingClassroomsNeeded(), 0);
		ArrayList<Pair<Integer, String>> numberStudents = new ArrayList<>();
		Pair<Integer, String> ingresantesCS = new Pair<>(50, "CS");
		numberStudents.add(ingresantesCS);
		distribution.setNumberStudents(numberStudents);
		distribution.generateDistribution();
		Assert.assertEquals(distribution.getNotExistingClassroomsNeeded(), 1);
	}
}