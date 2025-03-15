package com.restApi.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        System.out.println("pasamos por qui*****************************  0");
        System.out.println("Inicializando base de datos...");
        try {
            if (debeInicializar()) {
                ejecutarScriptSQL();
                System.out.println("Base de datos inicializada correctamente.");
            } else {
                System.out.println("pasamos por qui*****************************  3");
                System.out.println("La base de datos ya contiene datos.");
            }
        } catch (Exception e) {
            System.err.println("Error durante la inicialización: " + e.getMessage());
        }
    }


    private boolean debeInicializar() {

        System.out.println("pasamos por qui*****************************  1");
        try {
            // Verificar si la tabla principal existe
            jdbcTemplate.queryForObject("SELECT 1 FROM Personas LIMIT 1", Integer.class);
            return false;
        } catch (Exception e) {
            return true; // Si la tabla no existe, retornar true para inicializar
        }
    }

    private void ejecutarScriptSQL() throws Exception {
        // Cargar archivo SQL desde recursos
        ClassPathResource resource = new ClassPathResource("data.sql");
        String sqlScript = FileCopyUtils.copyToString(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)
        );

        // Dividir en sentencias y ejecutar
        for (String sentencia : sqlScript.split(";")) {
            String sql = sentencia.trim();
            if (!sql.isEmpty()) {
                jdbcTemplate.execute(sql);
            }
        }
    }


}