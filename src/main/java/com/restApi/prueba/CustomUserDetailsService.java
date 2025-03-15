package com.restApi.prueba;

// ROLES DEL USUARIO ENUMERADOR ROLES
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userDetailsService")

//implementando del frameworck
public class CustomUserDetailsService implements UserDetailsService { // Cambiado a UserDetailsService

    @Autowired
    private PersonaRepository personaRepository;

    //sobre escribienido una funcion  ya existente loadUserByUsername(String correo) de la clase  UserDetailsService se le
    //entrega un correo y si lo encuentra creamo el -----> springframework.security.core.userdetails.User.builder()

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

         Optional<Persona> persona = personaRepository.findByEmail(correo);
       // Optional<Persona> usuario = personaRepository.findByCorreo(correo);
        //TipoPersona
        System.out.println(" +++++++++++++++++++++++++++++++++++++++tipo persona+++++++++++++++++++++++++++"+ persona.get().getTipo().name());

        if (persona.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado con correo: " + correo);
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