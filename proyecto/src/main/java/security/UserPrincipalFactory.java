package security;

import data.entities.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserPrincipalFactory {

    public static UserPrincipal build (User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new UserPrincipal(user.getEmail(),"asdasdaa",authorities);
        
    }
    
}
