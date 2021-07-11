package controller;

import service.WhiteListService;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import data.entities.User;
import data.dtos.*;



@RestController
@RequestMapping("/whitelist")
@CrossOrigin
public class WhiteListController {

    @Autowired
    private WhiteListService whileListService;

    @PostMapping
    public User postUser(@RequestBody UserDTO userDTO){
        return whileListService.save(userDTO);
    }

    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", whileListService.updateUser(userDTO,id));
        map.put("message", "Usuario actualizado!");

        return new ResponseEntity(map, HttpStatus.OK);
    }


    @GetMapping("/show/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", whileListService.findOneById(id));
        map.put("message", "Usuario obtenido");

        return new ResponseEntity(map, HttpStatus.OK);
    }

    @PostMapping("/datatable")
    public ResponseEntity<?>getUsers() throws IOException
    {
        return new ResponseEntity(whileListService.getData(), HttpStatus.OK);
    }
}