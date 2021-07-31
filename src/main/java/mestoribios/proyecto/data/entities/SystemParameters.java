package mestoribios.proyecto.data.entities;

import java.util.ArrayList;
import java.util.List;


public class SystemParameters {

    private List<Ingresante> listIngresenates;

    public SystemParameters() {
        listIngresenates = new ArrayList<Ingresante>();
    }

    public SystemParameters(List<Ingresante> listIngresenates) {
        this.listIngresenates = listIngresenates;
    }

    public List<Ingresante> getListIngresenates() {
        return listIngresenates;
    }

    public void setListIngresenates(List<Ingresante> listIngresenates) {
        this.listIngresenates = listIngresenates;
    }
}