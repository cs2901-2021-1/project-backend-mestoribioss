package mestoribios.proyecto.controller;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import mestoribios.proyecto.data.dtos.TokenDTO;
import mestoribios.proyecto.data.entities.User;
import mestoribios.proyecto.service.AuthService;
import mestoribios.proyecto.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.api.client.json.jackson2.JacksonFactory;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collections;
import java.util.HashMap;

@RestController
@RequestMapping("/oauth")
@CrossOrigin
public class OAuthController {
    

    @Autowired
    Environment env;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

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
            TokenDTO tokenRes = authService.login(user);
            map.put("message","Usuario inicio sessión satisfactoriamente");
            map.put("token",tokenRes.getValue());
            map.put("user",user);
            return new ResponseEntity(map, HttpStatus.OK);
        }else{
            map.put("message", "Usuario no se encuentra en la WhiteList");
            return new ResponseEntity(map,HttpStatus.UNAUTHORIZED);
        }
        
    }catch(Exception e){
        map.put("error", e.getMessage());
        return new ResponseEntity(map,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/google/profile")
    public ResponseEntity<?> googleProfile() {
        HashMap<String, Object> map = new HashMap<>();
        try{
            map.put("user",authService.getProfile());
            // map.put("principal",principal.getName());
            return new ResponseEntity(map, HttpStatus.OK);
        }catch(Exception e){
            map.put("error", e.getMessage());
            return new ResponseEntity(map,HttpStatus.BAD_REQUEST);
            }
    }

}
