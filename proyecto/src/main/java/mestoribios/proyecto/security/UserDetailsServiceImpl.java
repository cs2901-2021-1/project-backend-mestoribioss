package mestoribios.proyecto.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mestoribios.proyecto.data.entities.User;
import mestoribios.proyecto.service.UserService;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user= userService.getByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Email no encontrado"));
        return UserPrincipalFactory.build(user);
    }
}
