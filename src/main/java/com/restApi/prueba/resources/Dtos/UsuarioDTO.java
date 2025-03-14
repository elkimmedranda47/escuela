/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restApi.prueba.resources.Dtos;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ekn47
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsuarioDTO {
    
    private Long id;
    private String nombre;
    private String correo;
    private String contrasena;
    private String comunidad;

    // Constructor, getters y setters
   

    public UsuarioDTO(String nombre, String correo, String contrasena, String comunidad) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.comunidad = comunidad;
    }


}
