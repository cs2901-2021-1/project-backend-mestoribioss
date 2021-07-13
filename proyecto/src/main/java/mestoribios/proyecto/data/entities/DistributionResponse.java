package mestoribios.proyecto.data.entities;
import java.util.Map;

import mestoribios.proyecto.data.entities.Classroom;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class DistributionResponse {

    Map<String, Integer> exist;
    Map<String, Integer> notExist;
    List<Classroom> classRooms;
    
    DistributionResponse() {
        this.exist = new HashMap<>();
        this.notExist = new HashMap<>();
        this.classRooms = new ArrayList<>();
    }

}