package com.restApi.prueba;

import com.restApi.prueba.http_errors.Role;
import com.restApi.prueba.models.Persona;
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

@Service("userDetailsService") // Cambiado a "userDetailsService"
public class CustomUserDetailsService implements UserDetailsService { // Cambiado a UserDetailsService

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

        Optional<Persona> usuario = personaRepository.findByCorreo(correo);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado con correo: " + correo);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.USUARIO.withPrefix()));

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.get().getCorreo())
                .password(usuario.get().getContrasena())
                .authorities(authorities)
                .build();
    }
}