package mestoribios.proyecto.data.entities;
import java.sql.ResultSet;

public class ClassroomResponse {
    String codAula;
    Integer capacity;
    String tipo;

    public ClassroomResponse(ResultSet rs) {
        try {
            this.codAula = rs.getString("NOMALIASAULA");
            this.capacity = rs.getInt("CAPACIDADAULA");
            // Lab de laboratorio, Aul de teoría y Aud de auditorio.
            this.tipo = rs.getString("DESCRIPCION").substring(0, 3); // Lab, Aul y Aud
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCodAula() {
        return codAula;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getTipo() {
        return tipo;
    }
}