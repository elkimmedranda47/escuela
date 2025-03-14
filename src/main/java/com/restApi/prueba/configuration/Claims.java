package com.restApi.prueba.configuration;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class Claims {
    private String mobile;
        private String name;
        private String role;

        private String gamil;


        // Getters y Setters
        public String getMobile() {
            return mobile;
        }


        public void setMobile(String mobile) {
            this.mobile = mobile;
        }


        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public String getRole() {
            return role;
        }


        public void setRole(String role) {
            this.role = role;
        }


        public String getSubject(){return this.mobile;}

        public String getGamil(){return this.gamil;}
        public void setGmail(String gmail){this.gamil=gmail;}


        public List<GrantedAuthority> gAuthorities(){
            return List.of(new SimpleGrantedAuthority(this.role));
        }
    
}
