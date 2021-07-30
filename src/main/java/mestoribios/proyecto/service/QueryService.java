package mestoribios.proyecto.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import mestoribios.proyecto.data.entities.ClassroomResponse;
import mestoribios.proyecto.data.entities.CourseResponse;

import java.sql.ResultSet;
import java.sql.SQLException;


@Service
@Transactional
public class QueryService {
    public List<CourseResponse> executeQueryCourses() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@coreqa4.cl2cyff2cklb.us-west-2.rds.amazonaws.com:1521:coreqa4", "desarrollo", "qad3vLE7Uk0110xor");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT CODMALLA, TEO_OR_LAB_ID, CODCURSO, NOMCURSO, SECCIONES, TOTALSESIONES, HORASSESION, CICLO FROM ((SELECT DISTINCT CODTIPOSESIONMTD AS TEO_OR_LAB_ID, aux.CODCURSO, NOMCURSO, SECCIONES, TOTALSESIONES, HORASSESION FROM (SELECT CODACTIVIDAD, IDACTIVIDAD AS CODCURSO, DESCRIPCIONCORTA AS NOMCURSO, COUNT(IDACTIVIDAD) AS SECCIONES FROM PROGRAMACION.PRO_CURSO_SECCION pcs CROSS JOIN PROGRAMACION.PRO_CURSO_PERIODO pcp CROSS JOIN PROGRAMACION.PRO_PERIODORANGO pp CROSS JOIN CONFIGURACION.CON_ACTIVIDAD ca CROSS JOIN PROGRAMACION.PRO_PERIODO pp2 WHERE pcs.CODCURSOPERIODO = pcp.CODCURSOPERIODO AND pcp.CODPERIODORANGO = pp.CODPERIODORANGO AND pcp.CODCURSO = ca.CODACTIVIDAD AND pp.CODPERIODO = pp2.CODPERIODO AND pcs.ISDELETED = 'N' AND pcp.ISDELETED = 'N' AND pp.ISDELETED = 'N' AND ca.ISDELETED = 'N' AND pp2.ISDELETED = 'N' AND NOMALIAS = '2021 - 1' GROUP BY CODACTIVIDAD, IDACTIVIDAD, DESCRIPCIONCORTA) aux CROSS JOIN CONFIGURACION.CON_CURSO_SESION ccs CROSS JOIN CONFIGURACION.CON_CURSO_SESION_CICLO ccsc WHERE aux.CODACTIVIDAD = ccs.CODCURSO AND ccs.CODCURSOSESION = ccsc.CODCURSOSESION AND ccs.ISDELETED = 'N' AND ccsc.ISDELETED = 'N' AND FRECUENCIAMTD = 47 AND TOTALHORAS != 0 AND TOTALSESIONES != 0 AND HORASSESION != 0) aux2 CROSS JOIN (SELECT cm.ALIAS AS CODMALLA, ca2.IDACTIVIDAD AS CODCURSOAUX, ca.ALIAS AS CICLO FROM CONFIGURACION.CON_AGRUPACION ca CROSS JOIN CONFIGURACION.CON_AGRUPACION_ACTIVIDAD caa CROSS JOIN CONFIGURACION.CON_MALLA cm CROSS JOIN CONFIGURACION.CON_ACTIVIDAD ca2 WHERE ca.CODAGRUPACION = caa.CODAGRUPACION AND ca.CODMALLA = cm.CODMALLA AND caa.CODACTIVIDAD = ca2.CODACTIVIDAD AND ca.ISDELETED = 'N' AND caa.ISDELETED = 'N' AND cm.ISDELETED = 'N' AND ca2.ISDELETED = 'N' AND ca.ALIAS != 'ELECTIVO')) WHERE aux2.CODCURSO = CODCURSOAUX AND REGEXP_LIKE(CODMALLA, '^[A-Z]{2}-2018-1') ORDER BY NOMCURSO, CODMALLA");
			List<CourseResponse> listaRpta = new ArrayList<>();
            while (rs.next()) listaRpta.add(new CourseResponse(rs));
            connection.close();
            return listaRpta;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<ClassroomResponse> executeQueryClassrooms() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@coreqa4.cl2cyff2cklb.us-west-2.rds.amazonaws.com:1521:coreqa4", "desarrollo", "qad3vLE7Uk0110xor");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT NOMALIASAULA, CAPACIDADAULA , DESCRIPCION FROM GENERAL.GEN_AULA ga CROSS JOIN GENERAL.GEN_TIPO_AULA gta WHERE ga.ISDELETED = 'N' AND ga.CODTIPOAULA = gta.CODTIPOAULA AND ga.CODTIPOAULA != 5 AND ga.CODTIPOAULA != 1 AND ga.CODTIPOAULA != 61");
			List<ClassroomResponse> listaRpta = new ArrayList<>();
            while (rs.next()) listaRpta.add(new ClassroomResponse(rs));
            connection.close();
            return listaRpta;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}