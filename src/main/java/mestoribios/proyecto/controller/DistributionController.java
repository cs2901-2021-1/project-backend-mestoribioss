package mestoribios.proyecto.controller;

import mestoribios.proyecto.config.Pair;
import mestoribios.proyecto.data.dtos.UserDTO;
import mestoribios.proyecto.data.entities.Distribution;
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

        Distribution distribution = new Distribution();

        ArrayList lista = new ArrayList<Pair<Integer, String>>();

        distributionDTO.forEach(dist -> {
            lista.add(new Pair<Integer, String>(Integer.parseInt(dist.get("numberStudents")), dist.get("major")));
        });


        ArrayList dummy = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> h1 = new HashMap<String, String>();
        HashMap<String, String> h2 = new HashMap<String, String>();

        h1.put("Salon teoria", "8");
        h2.put("Salon teoria", "2");

        dummy.add(h1);
        dummy.add(h2);

        return new ResponseEntity(dummy, HttpStatus.OK);
        //TODO: descomentar cuando ya este arreglado lo de los cursos (da error porq no hay cursos)
        //distribution.setNumberStudents(lista);
        //distribution.generateDistribution();
        //return new ResponseEntity(distribution.showDistributionFront(), HttpStatus.OK);
    }

}
