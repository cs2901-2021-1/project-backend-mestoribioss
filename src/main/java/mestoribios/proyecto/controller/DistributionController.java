package mestoribios.proyecto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/distribution")
@CrossOrigin
public class DistributionController {

    static final Logger logger = Logger.getLogger(DistributionController.class.getName());

    @PostMapping
    public ResponseEntity<?> generateDistribution(@RequestBody List<HashMap<String,String>> distributionDTO) {

        ArrayList<HashMap<String, String>> dummy = new ArrayList<>();
        HashMap<String, String> h1 = new HashMap<>();
        HashMap<String, String> h2 = new HashMap<>();
        h1.put("Salon teoria", "8");
        h2.put("Salon teoria", "2");
        dummy.add(h1);
        dummy.add(h2);

        return new ResponseEntity<>(dummy, HttpStatus.OK);

        /*
        descomentar cuando ya este arreglado lo de los cursos (da error porq no hay cursos)
        no borrar esto
        Distribution distribution = new Distribution();
        ArrayList <Pair<Integer, String>> lista = new ArrayList<>();
        distributionDTO.forEach(dist -> {
            lista.add(new Pair<>(Integer.parseInt(dist.get("numberStudents")), dist.get("major")));
        });
        distribution.setNumberStudents(lista);
        distribution.generateDistribution();
        return new ResponseEntity(distribution.showDistributionFront(), HttpStatus.OK);
         */
    }

}
