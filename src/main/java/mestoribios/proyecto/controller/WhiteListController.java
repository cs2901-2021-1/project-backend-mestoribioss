package mestoribios.proyecto.controller;

import java.util.HashMap;

import mestoribios.proyecto.data.dtos.DatatableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mestoribios.proyecto.data.dtos.UserDTO;
import mestoribios.proyecto.data.entities.User;
import mestoribios.proyecto.service.UserService;



@PreAuthorize("hasAuthority('admin')")
@RestController
@RequestMapping("/whitelist")
@CrossOrigin
public class WhiteListController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User postUser(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    
    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<String, Object>> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        HashMap<String, Object> map = new HashMap<>();
        try{
            map.put("data", userService.updateUser(userDTO,id));
            map.put("message", "Usuario actualizado!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        catch(Exception e){
            map.put("error", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/show/{id}")
    public ResponseEntity<HashMap<String, Object>> getUserById(@PathVariable Long id){
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", userService.findOneById(id));
        map.put("message", "Usuario obtenido");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/datatable")
    public ResponseEntity<DatatableDTO>getUsers() {
        return new ResponseEntity<>(userService.getData(), HttpStatus.OK);
    }
}