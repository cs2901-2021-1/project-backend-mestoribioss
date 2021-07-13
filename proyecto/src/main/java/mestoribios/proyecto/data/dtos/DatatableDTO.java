package mestoribios.proyecto.data.dtos;


import java.util.List;

import mestoribios.proyecto.data.entities.User;

public class DatatableDTO {
    private List<User> data;
    private Integer recordsTotal;
    private Integer recordsFiltered;

    public DatatableDTO(){}

    public List<User> getData() {
        return data;
    }
    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }
    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
    public Integer getRecordsTotal() {
        return recordsTotal;
    }
    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
    public void setData(List<User> data) {
        this.data = data;
    }

    

 

 }

    

    

