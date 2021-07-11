package mestoribios.proyecto.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import mestoribios.proyecto.data.entities.User;

public class UserPrincipalFactory {

    public static UserPrincipal build (User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new UserPrincipal(user.getEmail(),user.getPassword(),authorities);
        
    }
    
}
