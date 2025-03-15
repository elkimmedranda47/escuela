package com.restApi.prueba.resources;


import com.restApi.prueba.CustomUserDetailsService;
import com.restApi.prueba.configuration.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(BasicAuthLogin.BASIC_AUTH)

public class BasicAuthLogin {

    public static final String BASIC_AUTH = "/basic-auth";

    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    //constructuro inicializa objeto CustomUserDetailsService---->userDetailsService
    @Autowired
    public BasicAuthLogin(CustomUserDetailsService userDetailsService, JwtService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String correo = loginData.get("correo");
        String password = loginData.get("password");

        System.out.println("Llegamos");
        System.out.println("Correo: " + correo + " Password: " + password);

        //le entregamos el correo a la funcion loadUserByUsername que es una funcion ya existente esta se sobre escribe,
        //le entregamos el correo al frameworck.

        UserDetails userDetails = userDetailsService.loadUserByUsername(correo);

        if (userDetails == null) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
        //hacer el maches con el userDetails y a contraseña que Viene del post
        return validatePasswordAndCreateToken(userDetails, password);
    }

    private ResponseEntity<?> validatePasswordAndCreateToken(UserDetails userDetails, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
        //encapsulado Username del usuarioDetails
        String mobile = userDetails.getUsername();

        String role = userDetails.getAuthorities().stream()
                .findFirst()//.stream() Convierte la colección de GrantedAuthority en un flujo
                .map(Object::toString)//Si findFirst() encuentra un rol, .map() transforma ese rol en una cadena de texto.
                .orElse("ROLE_USER");//Si findFirst() devolvió un Optional.empty() (el usuario no tiene roles), .orElse()

        // si existe el usuaio  {   UserDetails userDetails = userDetailsService.loadUserByUsername(correo); }
        // y su contraseña hace maches con la bd se crea el token
        String token = jwtService.crearToken(mobile, role);

        System.out.println("Token: " + token);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(Map.of(
                        "token", token,
                        "user", Map.of(
                                "mobile", mobile,
                                "role", role
                        ),
                        "expiresIn", jwtService.obtenerTiempoExpiracion()
                ));
    }

    //validar si el token esta corectamente firmado
    @GetMapping("/Acceso/ValidarToken")
    public ResponseEntity<Map<String, Boolean>> isTokenValid(@RequestParam String token) {
        boolean isValid = jwtService.verificar2(token);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isSuccess", isValid);
        return ResponseEntity.ok(response);
    }
}
