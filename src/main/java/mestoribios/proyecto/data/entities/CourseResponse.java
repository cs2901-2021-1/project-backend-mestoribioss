package mestoribios.proyecto.data.entities;
import java.sql.ResultSet;

public class CourseResponse {
    String codMalla;
    String type; // Teoría xor Laboratorio
    String codCurso;
    String nomCurso;
    Integer secciones;
    Integer horasSemanales;
    String ciclo; // Ciclo 1, Ciclo 2, ..., Ciclo 10, ELECTIVO

    public CourseResponse(ResultSet rs) {
        try {
            this.codMalla = rs.getString("CODMALLA");
            int aux = rs.getInt("TEO_OR_LAB_ID");
            if (aux == 7 || aux == 9) // teo o teoría virtual
                this.type = "Teoría";
            else if (aux == 8 || aux == 10) // teo o teoría virtual
                this.type = "Laboratorio";
            this.codCurso = rs.getString("CODCURSO");
            this.nomCurso = rs.getString("NOMCURSO");
            this.secciones = rs.getInt("SECCIONES");
            this.horasSemanales = rs.getInt("TOTALSESIONES")*rs.getInt("HORASSESION")/16;
            this.ciclo = rs.getString("CICLO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCodMalla() {
        return codMalla;
    }

    public String getType() {
        return type;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public String getNomCurso() {
        return nomCurso;
    }

    public int getSecciones() {
        return secciones;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public String getCiclo() {
        return ciclo;
    }
}