package com.Bgy.CHL.AdminTareas.infra.Security;


import com.Bgy.CHL.AdminTareas.Domain.Usuario.Usuario;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("Bgy").withSubject(usuario.getUsuario())
                    .withClaim("id",usuario.getId()).withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al crear el token",exception);
        }
    }

    public String getSubject(String token){
        DecodedJWT verifier= null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("Bgy")
                    .build().verify(token)
            ;
            verifier.getSubject();

        } catch (JWTVerificationException exception){
            // Invalid signature/claims
        }

        if(verifier.getSubject()==null){
            throw new RuntimeException("verifier invalid");
        }
        return verifier.getSubject();

    }




    private Instant generarFechaExpiracion( ){

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}

