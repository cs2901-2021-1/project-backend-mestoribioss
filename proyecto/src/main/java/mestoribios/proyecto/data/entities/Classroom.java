package mestoribios.proyecto.data.entities;

public class Classroom
{
    private Integer id;
    private String tipo;
    private Integer aforo;
    private String [][] timeSchedule;

    Classroom(){
        timeSchedule = new String[15][6];
    }

    public String [][] getTimeSchedule() {
        return timeSchedule;
    }

    public void setTimeSchedule(String [][] timeSchedule) {
        this.timeSchedule = timeSchedule;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}