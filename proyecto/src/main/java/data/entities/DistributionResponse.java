package data.entities;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import data.entities.Classroom;

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