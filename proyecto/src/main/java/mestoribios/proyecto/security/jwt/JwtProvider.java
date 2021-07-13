package mestoribios.proyecto.security.jwt;

import io.jsonwebtoken.*;
import mestoribios.proyecto.security.UserPrincipal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Autowired
    Environment env;
    

    public String generateToken(Authentication authentication){
        UserPrincipal usuarioPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Integer.parseInt(env.getProperty("jwt.expiration"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("jwt.secret").toString())
                .compact();
    }

    public String getEmailFromToken(String token){
        return Jwts.parser().setSigningKey(env.getProperty("jwt.secret").toString()).parseClaimsJws(token).getBody().getSubject();
    }


    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(env.getProperty("jwt.secret").toString()).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException | UnsupportedJwtException | ExpiredJwtException | IllegalArgumentException | SignatureException e){
            logger.error("error comprobando el token");
            e.printStackTrace();
        }
        return false;
    }

}
