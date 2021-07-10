package service;

import service.custom_exceptions.CustomNotFoundException;
import data.dtos.UserDTO;
import data.entities.User;
import data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WhiteListService {
    @Autowired
    private UserRepository userRepository;

    // Añadir a la tabla usuario para que tenga permiso
    public User save(UserDTO userDTO){
        var user = new User();
        user.setLastName(userDTO.getLastName());
        user.setName(userDTO.getName());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }

    // Quitarle los permisos a un usuario de la whitelist
    public void deleteById(Long id){
        User user = findOneById(id);
        user.setStatus(0);
        userRepository.save(user);
    }

    public User findOneById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) return userOptional.get();
        else throw new CustomNotFoundException("¡El usuario con el id " + id + " no existe!");
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public List<User> getAllNoDeleted(){
        List<User> allUsers = getAll();
        List<User> usersNoDeleted = new ArrayList<>();
        for (var user : allUsers) {
            if (user.getStatus() == 1){
                usersNoDeleted.add(user);
            }
        }
        return usersNoDeleted;
    }
}