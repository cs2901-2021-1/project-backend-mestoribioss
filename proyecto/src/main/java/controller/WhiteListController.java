package controller;

import service.WhiteListService;

import java.io.IOException;

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
    public User updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
        return whileListService.updateUser(userDTO,id);
    }


    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return whileListService.findOneById(id);
    }

    @PostMapping("/datatable")
    public ResponseEntity<?>getUsers() throws IOException
    {
        return new ResponseEntity(whileListService.getData(), HttpStatus.OK);
    }
}