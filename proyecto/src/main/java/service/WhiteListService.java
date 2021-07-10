package service;

import service.custom_exceptions.CustomNotFoundException;
import data.dtos.UserDTO;
import data.entities.User;
import data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class WhiteListService {
    @Autowired
    private UserRepository userRepository;

    // Añadir a la tabla usuario para que tenga permiso
    public User save(UserDTO userDTO){
        
    }

    // Quitarle los permisos a un usuario de la whitelist
    public void deleteById(Long id){

    }

    public User findOneById(Long id){

    }
}