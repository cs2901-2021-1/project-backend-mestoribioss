package controller;

import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.Value;
import data.dtos.TokenDTO;
import service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;
import java.util.HashMap;

@RestController
@RequestMapping("/oauth")
@CrossOrigin
public class OAuthController {
    
    @Value("${google.clientId}")
    String googleClientId;

    @Autowired
    private AuthService authService;


    @PostMapping("/google")
    public ResponseEntity<?> google(@RequestBody TokenDTO TokenDTO) {
        HashMap<String, Object> map = new HashMap<>();
        try{
        final NetHttpTransport transport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder verifier =
                 new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                 .setAudience(Collections.singletonList(googleClientId));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), TokenDTO.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
        if(authService.isInWhitelist(payload.getEmail())){
            return new ResponseEntity(payload, HttpStatus.OK);
        }else{
            map.put("message", "Usuario no esta en la whiteList");
            return new ResponseEntity(map,HttpStatus.UNAUTHORIZED);
        }
         // Usuario usuario = new Usuario();
        // if(usuarioService.existsEmail(payload.getEmail()))
        //     usuario = usuarioService.getByEmail(payload.getEmail()).get();
        // else
        //     usuario = saveUsuario(payload.getEmail());
        // TokenDTO tokenRes = login(usuario);
    }catch(Exception e){
        map.put("error", e.getMessage());
        return new ResponseEntity(map,HttpStatus.BAD_REQUEST);
        }
    }
}
