package com.restApi.prueba;

// ROLES DEL USUARIO ENUMERADOR ROLES
import com.restApi.prueba.configuration.JwtService;

import com.restApi.prueba.http_errors.BadRequestException;
import com.restApi.prueba.http_errors.Role;


import com.restApi.prueba.models.Persona;
import com.restApi.prueba.models.TipoPersona;
import com.restApi.prueba.repositorys.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.restApi.prueba.http_errors.AccessDeniedException;
//import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userDetailsService")

//implementando del frameworck
public class CustomUserDetailsService implements UserDetailsService {


    private final JwtService jwtService; // Cambiado a UserDetailsService

    @Autowired
    private PersonaRepository personaRepository;

    public CustomUserDetailsService(JwtService jwtService) {
        this.jwtService=jwtService;
    }

    //sobre escribienido una funcion  ya existente loadUserByUsername(String correo) de la clase  UserDetailsService se le
    //entrega un correo y si lo encuentra creamo el -----> springframework.security.core.userdetails.User.builder()

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

         Optional<Persona> persona = personaRepository.findByEmail(correo);
       // Optional<Persona> usuario = personaRepository.findByCorreo(correo);
        //TipoPersona
        if (persona.isEmpty()) {
            System.out.println(" +++++++++++++++++++++++++++++++++++++++!!! persona Error !!!+++++++++++++++++++++++++++");
           throw new UsernameNotFoundException("Correo o contraseña invalidos");

            //throw new AccessDeniedException("Account is inactive.");
            //throw new BadRequestException("!!!El usuairo o la contraseña son incorrectos!!!");


        }
        //buscar los roles en bd
        //con este odjeto--->Persona buscar los roles de persona y crear la lista de authorities  List<GrantedAuthority>
        List<GrantedAuthority> authorities = new ArrayList<>();
        //authorities.add(new SimpleGrantedAuthority(Role.USUARIO.withPrefix()));
        //authorities.add(new SimpleGrantedAuthority(Role.persona.get().getTipo().name().withPrefix()));
        authorities.add(new SimpleGrantedAuthority(persona.get().getTipo().name()));

        //con persona ya encapsulado creamos el userdetails con  persona.get().getEmail() persona.get().getPassword()
        return org.springframework.security.core.userdetails.User.builder()
                .username(persona.get().getEmail())
                .password(persona.get().getPassword())
                .authorities(authorities)
                .build();
    }
}