package mestoribios.proyecto.service;

import mestoribios.proyecto.data.dtos.DatatableDTO;
import mestoribios.proyecto.data.dtos.UserDTO;
import mestoribios.proyecto.data.entities.User;
import mestoribios.proyecto.data.repositories.UserRepository;
import mestoribios.proyecto.service.custom_exceptions.CustomNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    Environment env;
    

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;

    private User modify(User user, UserDTO userDTO){
        user.setLastName(userDTO.getLastName());
        user.setName(userDTO.getName());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }

    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean existsEmail(String email){
        return userRepository.existsByEmail(email);
    }

    // Añadir a la tabla usuario para que tenga permiso
    public User save(UserDTO userDTO){
        var user = new User();
        user.setPassword(passwordEncoder.encode(env.getProperty("secretPsw")));
        return modify(user, userDTO);
    }

    // Quitarle los permisos a un usuario de la whitelist
    public User deleteById(Long id){
        User user = findOneById(id);
        user.setStatus(0);
        return userRepository.save(user);
    }

    public User updateUser(UserDTO userDTO, Long id){
        User user = findOneById(id);
        return modify(user, userDTO);
    }

    public User findOneById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) return userOptional.get();
        else throw new CustomNotFoundException("¡El usuario con el id " + id + " no existe!");
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public DatatableDTO getData(){
        List<User> data =  getAll();
        Integer recordsTotal = data.size();
        Integer recordsFiltered = 0;
        DatatableDTO datatableDTO = new DatatableDTO();
        datatableDTO.setData(data);
        datatableDTO.setRecordsTotal(recordsTotal);
        datatableDTO.setRecordsFiltered(recordsFiltered);
        return datatableDTO;
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

    public User save(User usuario){
        return userRepository.save(usuario);
    }
}