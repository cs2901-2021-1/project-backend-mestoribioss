package data.dtos;


import java.util.List;
import data.entities.User;

public class DatatableDTO {
    List<User> data;
    Integer recordsTotal;
    Integer recordsFiltered;

    public DatatableDTO(List<User> list, Integer recordsTotal, Integer recordsFiltered) {
        this.data = list;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
    }

 

 }

    

    

