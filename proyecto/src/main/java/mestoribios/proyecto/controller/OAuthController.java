package mestoribios.proyecto.controller;

import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.Value;

import mestoribios.proyecto.data.dtos.TokenDTO;
import mestoribios.proyecto.data.entities.User;
import mestoribios.proyecto.security.jwt.JwtProvider;
import mestoribios.proyecto.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.crypto.password.PasswordEncoder;
import com.google.api.client.json.jackson2.JacksonFactory;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Collections;
import java.util.HashMap;

@RestController
@RequestMapping("/oauth")
@CrossOrigin
public class OAuthController {
    
    // @Value("${google.clientId}")
    // private String googleClientId;

    // @Value("${secretPsw}")
    // private String secretPsw;

    @Autowired
    Environment env;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserService userService;




    @PostMapping("/google")
    public ResponseEntity<?> google(@RequestBody TokenDTO TokenDTO) {
        HashMap<String, Object> map = new HashMap<>();
        try{
        final NetHttpTransport transport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder verifier =
                 new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                 .setAudience(Collections.singletonList(env.getProperty("google.clientId").toString()));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), TokenDTO.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
        User user= new User();
        if(userService.existsEmail(payload.getEmail())){
            user=userService.getByEmail(payload.getEmail()).get();
           
        }else{
            user=saveUsuario(payload.getEmail(), (String) payload.get("name"), (String) payload.get("name"));
        }

        TokenDTO tokenRes = login(user);
        return new ResponseEntity(tokenRes, HttpStatus.OK);

        // map.put("message", "Usuario no esta en la whiteList");
        // return new ResponseEntity(map,HttpStatus.UNAUTHORIZED);
        
    }catch(Exception e){
        map.put("error", e.getMessage());
        return new ResponseEntity(map,HttpStatus.BAD_REQUEST);
        }
    }

    private TokenDTO login(User user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), env.getProperty("secretPsw").toString()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        TokenDTO tokenDto = new TokenDTO();
        tokenDto.setValue(jwt);
        return tokenDto;
    }

    private User saveUsuario(String email,String name,String lastName){
        User usuario = new User(email, passwordEncoder.encode(env.getProperty("secretPsw").toString()),1,name,lastName);;
        return userService.save(usuario);
    }
}
