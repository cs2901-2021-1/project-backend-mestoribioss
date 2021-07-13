package mestoribios.proyecto.seeders;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import mestoribios.proyecto.data.repositories.UserRepository;
import mestoribios.proyecto.data.entities.User;


@Component
public class DataBaseSeeder {
    private final static Logger logger = LoggerFactory.getLogger(DataBaseSeeder.class);


    @Autowired
    Environment env;
    

    @Autowired
    PasswordEncoder passwordEncoder;

    // @Autowired
    private UserRepository userRepository;

    @Autowired
    public DataBaseSeeder(
        UserRepository userRepository
    ){
        this.userRepository = userRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUsers();
    }

    private void seedUsers() {
        List<User> users=getBaseUsers();
        for (int i = 0; i < users.size(); i++) {
            User usertmp=users.get(i);
            if(!userRepository.existsByEmail(usertmp.getEmail())){
                userRepository.save(usertmp);
            }
        }
    }

    private List<User> getBaseUsers(){
        List<User> users= new ArrayList<User>();
        String password=passwordEncoder.encode(env.getProperty("secretPsw").toString());
        users.add(new User("jorge.vasquez@utec.edu.pe",password,"admin","Jorge","Vásquez"));
        return users;
    }


}
