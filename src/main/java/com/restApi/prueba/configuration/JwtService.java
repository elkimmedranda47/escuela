package com.restApi.prueba.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtService {
    private static final String BEARER = "Bearer ";
    private static final String MOBILE_CLAIM = "mobile";
   // private static final String NAME_CLAIM = "name";
    private static final String ROLE_CLAIM = "role";
    private static final String GMAIL_CLAIM = "gmail";


    private final String secreto;//secret
    private final String emisor;//issuer
    private final int expiracion; //expiration

    @Autowired
    public JwtService(@Value("${miw.jwt.secret}") String secreto, @Value("${miw.jwt.issuer}") String emisor,
                      @Value("${miw.jwt.expire}") int expiracion) {
        
        this.secreto = secreto;
        this.emisor = emisor;
        this.expiracion = expiracion;

    }

    public String extraerTokenBearer(String bearer) {
        if (bearer != null && bearer.startsWith(BEARER) && 3 == bearer.split("\\.").length) {
            return bearer.substring(BEARER.length());
        } else {
            return "";
        }
    }

    public String crearToken(String movil, /*String nombre,*/ String rol) {
        return JWT.create()
                .withIssuer(this.emisor)
                .withIssuedAt(new Date())
                .withNotBefore(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + this.expiracion * 1000))
                .withClaim(MOBILE_CLAIM, movil)
                //.withClaim(NAME_CLAIM, nombre)
                .withClaim(ROLE_CLAIM, rol)
                .sign(Algorithm.HMAC256(this.secreto));
    }

    public int obtenerTiempoExpiracion() {
        return this.expiracion;
    }

    public String obtenerMovil(String autorizacion) {
        return this.verificar(autorizacion)
                .map(jwt -> jwt.getClaim(MOBILE_CLAIM).asString())
                .orElse("");
    }

    public String obtenerRol(String autorizacion) {
        System.out.println(" obtenerRol(String autorizacion) ------->  "
                + " this.verificar(autorizacion)*****************************"+this.verificar(autorizacion));

        return this.verificar(autorizacion)
                .map(jwt -> jwt.getClaim(ROLE_CLAIM).asString())
                .orElse("");
    }
     // Función para verificar si el token es válido
      public boolean isTokenValid(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(this.secreto))
                    .withIssuer(this.emisor)
                    .build()
                    .verify(token);
            // Aquí puedes agregar más verificaciones si lo deseas (ej: expiración)
            return true;
        } catch (Exception e) {
            return false;  // Si hay un problema al verificar el token, se considera no válido
        }
    }

    public Optional<DecodedJWT> verificar(String token) {
        try {

            System.out.println("JWT.require(Algorithm.HMAC256(this.secreto))****************************"+JWT.require(Algorithm.HMAC256(this.secreto)));
            return Optional.of(JWT.require(Algorithm.HMAC256(this.secreto))
                    .withIssuer(this.emisor).build()
                    .verify(token));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
    
    // Función para obtener las claims del token
    public Claims getClaims(String token) {

        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(this.secreto))
                    .withIssuer(this.emisor)
                    .build()
                    .verify(token);

            // Devuelve las claims desde el JWT
            Claims claims = new Claims();
            claims.setMobile(decodedJWT.getClaim(MOBILE_CLAIM).asString());
            //claims.setName(decodedJWT.getClaim(NAME_CLAIM).asString());
            claims.setRole(decodedJWT.getClaim(ROLE_CLAIM).asString());
            claims.setGmail(decodedJWT.getClaim(GMAIL_CLAIM).asString());



            return claims;
        } catch (Exception e) {
            return null;  // En caso de error al obtener las claims
        }
    }


    public boolean verificar2(String n) {
        try {
            String token ="reyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJlcy11cG0tdXBtIiwiaWF0IjoxNzI3MzE1Nzg2LCJuYmYiOjE3MjczMTU3ODYsImV4cCI6MTcyNzMxOTM4NiwibW9iaWxlIjoiMSIsInJvbGUiOiJST0xFX0NVU1RPTUVSIn0.FIkP9kWj7hEo7ifkyhW83QriyKSPpiVD90vLvU8FhXE";

            System.out.println("************verificar2***************");
            JWT.require(Algorithm.HMAC256(this.secreto))
                .withIssuer(this.emisor)
                .build()
                .verify(n);
            return true; // Token es válido
        } catch (Exception exception) {
            return false; // Token inválido o excepción
        }
    }


    public String extractUsername(String token) {
        return this.verificar(token)
                .map(jwt -> jwt.getClaim(MOBILE_CLAIM).asString()) // o GMAIL_CLAIM si usas email
                .orElseThrow(() -> new JWTVerificationException("Token inválido"));
    }



}