/*package com.restApi.prueba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private DatabaseClient databaseClient;

    @Override
    public void run(String... args) {
        System.out.println("Ejecutando la inicialización de la base de datos...");
        executeSql().subscribe(
            null,
            error -> System.out.println("Error al ejecutar SQL: " + error.getMessage()),
            () -> System.out.println("Inicialización de la base de datos completada.")
        );
    }

    private Mono<Void> executeSql() {
      String sql = "CREATE TABLE IF NOT EXISTS STYLE (\n" +
                    "    id SERIAL PRIMARY KEY,\n" +
                    "    name VARCHAR(255) NOT NULL\n" +
                    ");\n" +
                    "CREATE TABLE IF NOT EXISTS AUTHOR (\n" +
                    "    id SERIAL PRIMARY KEY,\n" +
                    "    name VARCHAR(255) NOT NULL,\n" +
                    "    email VARCHAR(255),\n" +
                    "    phone VARCHAR(20),\n" +
                    "    style_id INT,\n" +
                    "    FOREIGN KEY (style_id) REFERENCES STYLE(id)\n" +
                    ");\n" +
                    "CREATE TABLE IF NOT EXISTS BOOK (\n" +
                    "    id SERIAL PRIMARY KEY,\n" +
                    "    isbn VARCHAR(255) NOT NULL\n" +
                    ");\n" +
                    "CREATE TABLE IF NOT EXISTS THEME (\n" +
                    "    id SERIAL PRIMARY KEY,\n" +
                    "    name VARCHAR(255) NOT NULL\n" +
                    ");\n" +
                    "CREATE TABLE IF NOT EXISTS BOOK_AUTHOR (\n" +
                    "    book_id INT NOT NULL,\n" +
                    "    author_id INT NOT NULL ,\n" +
                    "    PRIMARY KEY (book_id, author_id),\n" +
                    "    FOREIGN KEY (book_id) REFERENCES BOOK(id) ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (author_id) REFERENCES AUTHOR(id) ON DELETE CASCADE\n" +
                    ");\n" +
                    "CREATE TABLE IF NOT EXISTS BOOK_THEME (\n" +
                    "    book_id INT NOT NULL,\n" +
                    "    theme_id INT NOT NULL,\n" +
                    "    PRIMARY KEY (book_id, theme_id),\n" +
                    "    FOREIGN KEY (book_id) REFERENCES BOOK(id) ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (theme_id) REFERENCES THEME(id) ON DELETE CASCADE\n" +
                    ");\n" +
                    "-- Insertar estilos\n" +
                    "INSERT INTO STYLE (name) VALUES ('Biography');\n" +
                    "INSERT INTO STYLE (name) VALUES ('Drama');\n" +
                    "INSERT INTO STYLE (name) VALUES ('Comedy');\n" +
                    "INSERT INTO STYLE (name) VALUES ('Poetry');\n" +
                    "INSERT INTO STYLE (name) VALUES ('Horror');\n" +
                    "INSERT INTO STYLE (name) VALUES ('Fantasy');\n" +
                    "INSERT INTO STYLE (name) VALUES ('Mystery');\n" +
                    "INSERT INTO STYLE (name) VALUES ('Romance');\n" +
                    "INSERT INTO STYLE (name) VALUES ('Science Fiction');\n" +
                    "INSERT INTO STYLE (name) VALUES ('Thriller');\n" +
                    "-- Insertar autores\n" +
                    "INSERT INTO AUTHOR (name, email, phone, style_id) VALUES ('Dave', 'author6@example.com', '678-901-2345', 1);\n" +
                    "INSERT INTO AUTHOR (name, email, phone, style_id) VALUES ('Eve', 'author7@example.com', '789-012-3456', 2);\n" +
                    "INSERT INTO AUTHOR (name, email, phone, style_id) VALUES ('Frank', 'author8@example.com', '890-123-4567', 3);\n" +
                    "INSERT INTO AUTHOR (name, email, phone, style_id) VALUES ('Grace', 'author9@example.com', '901-234-5678', 4);\n" +
                    "INSERT INTO AUTHOR (name, email, phone, style_id) VALUES ('Hank', 'author10@example.com', '012-345-6789', 5);\n" +
                    "-- Insertar libros\n" +
                    "INSERT INTO BOOK (isbn) VALUES ('Book One');\n" +
                    "INSERT INTO BOOK (isbn) VALUES ('Book Two');\n" +
                    "INSERT INTO BOOK (isbn) VALUES ('Book Three');\n" +
                    "INSERT INTO BOOK (isbn) VALUES ('Book Four');\n" +
                    "INSERT INTO BOOK (isbn) VALUES ('Book Five');\n" +
                    "-- Insertar temáticas\n" +
                    "INSERT INTO THEME (name) VALUES ('Comedy');\n" +
                    "INSERT INTO THEME (name) VALUES ('Drama');\n" +
                    "INSERT INTO THEME (name) VALUES ('Science Fiction');\n" +
                    "INSERT INTO THEME (name) VALUES ('Fantasy');\n" +
                    "INSERT INTO THEME (name) VALUES ('Biography');\n" +
                    "-- Relacionar autores con libros\n" +
                    "INSERT INTO BOOK_AUTHOR (book_id, author_id) VALUES (1, 1);\n" +
                    "INSERT INTO BOOK_AUTHOR (book_id, author_id) VALUES (2, 2);\n" +
                    "INSERT INTO BOOK_AUTHOR (book_id, author_id) VALUES (3, 3);\n" +
                    "INSERT INTO BOOK_AUTHOR (book_id, author_id) VALUES (4, 4);\n" +
                    "INSERT INTO BOOK_AUTHOR (book_id, author_id) VALUES (5, 5);\n" +
                    "-- Relacionar libros con temáticas\n" +
                    "INSERT INTO BOOK_THEME (book_id, theme_id) VALUES (1, 1);\n" +
                    "INSERT INTO BOOK_THEME (book_id, theme_id) VALUES (2, 2);\n" +
                    "INSERT INTO BOOK_THEME (book_id, theme_id) VALUES (3, 3);\n" +
                    "INSERT INTO BOOK_THEME (book_id, theme_id) VALUES (4, 4);\n" +
                    "INSERT INTO BOOK_THEME (book_id, theme_id) VALUES (5, 5);";
        // Divide el script SQL en declaraciones individuales
        String[] statements = sql.split(";");
        Mono<Void> mono = Mono.empty();

        for (String statement : statements) {
            if (!statement.trim().isEmpty()) {
                mono = mono.then(databaseClient.sql(statement.trim()).fetch().rowsUpdated().then());
            }
        }

        return mono;
    }
}
*/
/*
package com.restApi.prueba;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private DatabaseClient databaseClient;

    @Override
    public void run(String... args) {
        System.out.println("Ejecutando la inicialización de la base de datos...");







        initializeDatabase()
            .subscribe(
                null,
                error -> System.out.println("Error al inicializar la base de datos: " + error.getMessage()),
                () -> System.out.println("Inicialización de la base de datos completada.")
            );

    }

    private Mono<Void> initializeDatabase() {
        String verifySql = "SELECT COUNT(*) AS count FROM Usuario";

        return databaseClient.sql(verifySql)
            .fetch()
            .first()
            .flatMap(result -> {
                long count = (long) result.get("count");
                if (count == 0) {
                    System.out.println("La base de datos está vacía. Ejecutando script de inicialización...");
                    return executeInsertStatements();
                } else {
                    System.out.println("La base de datos ya contiene datos. No se ejecutará el script de población.");
                    return Mono.empty();
                }
            })
            .onErrorResume(e -> {
                System.out.println("Error al verificar la base de datos (posiblemente no existe). Inicializando desde cero...");
                return executeInsertStatements();
            });
    }

    private Mono<Void> executeInsertStatements() {
        String sql = "-- Tabla: Usuario\n" +
        "CREATE TABLE Usuario (\n" +
        "    id SERIAL PRIMARY KEY,\n" +
        "    nombre VARCHAR(100) NOT NULL,\n" +
        "    correo VARCHAR(100) UNIQUE NOT NULL,\n" +
        "    contrasena VARCHAR(255) NOT NULL,\n" +
        "    comunidad VARCHAR(50)\n" +
        ");\n" +
        "\n" +
        "-- Tabla: Categoria\n" +
        "CREATE TABLE Categoria (\n" +
        "    id SERIAL PRIMARY KEY,\n" +
        "    nombre VARCHAR(50) NOT NULL\n" +
        ");\n" +
        "\n" +
        "-- Tabla: Reporte\n" +
        "CREATE TABLE Reporte (\n" +
        "    id SERIAL PRIMARY KEY,\n" +
        "    titulo VARCHAR(200) NOT NULL,\n" +
        "    descripcion TEXT NOT NULL,\n" +
        "    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
        "    ubicacion VARCHAR(100),\n" +
        "    urgencia VARCHAR(20) CHECK (urgencia IN ('Baja', 'Media', 'Alta', 'Urgente')) NOT NULL,\n" +
        "    estado VARCHAR(20) CHECK (estado IN ('En revisión', 'Resuelto', 'Pendiente')) DEFAULT 'Pendiente',\n" +
        "    usuario_id INT,\n" +
        "    categoria_id INT,\n" +
        "    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),\n" +
        "    FOREIGN KEY (categoria_id) REFERENCES Categoria(id)\n" +
        ");\n" +
        "\n" +
        "-- Tabla: Comentario\n" +
        "CREATE TABLE Comentario (\n" +
        "    id SERIAL PRIMARY KEY,\n" +
        "    contenido TEXT NOT NULL,\n" +
        "    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
        "    usuario_id INT,\n" +
        "    reporte_id INT,\n" +
        "    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),\n" +
        "    FOREIGN KEY (reporte_id) REFERENCES Reporte(id)\n" +
        ");\n" +
        "\n" +
        "-- Tabla: MeGusta\n" +
        "CREATE TABLE MeGusta (\n" +
        "    id SERIAL PRIMARY KEY,\n" +
        "    usuario_id INT,\n" +
        "    reporte_id INT,\n" +
        "    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
        "    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),\n" +
        "    FOREIGN KEY (reporte_id) REFERENCES Reporte(id)\n" +
        ");\n" +
        "\n" +
        "-- Tabla: Rol\n" +
        "CREATE TABLE Rol (\n" +
        "    id SERIAL PRIMARY KEY,  -- Corregido a SERIAL\n" +
        "    nombre VARCHAR(50) UNIQUE NOT NULL\n" +
        ");\n" +
        "\n" +
        "-- Tabla: Permiso\n" +
        "CREATE TABLE Permiso (\n" +
        "    id SERIAL PRIMARY KEY,  -- Corregido a SERIAL\n" +
        "    codigo VARCHAR(50) UNIQUE NOT NULL\n" +
        ");\n" +
        "\n" +
        "-- Tabla: RolPermiso\n" +
        "CREATE TABLE RolPermiso (\n" +
        "    rol_id INT,\n" +
        "    permiso_id INT,\n" +
        "    PRIMARY KEY (rol_id, permiso_id),\n" +
        "    FOREIGN KEY (rol_id) REFERENCES Rol(id),\n" +
        "    FOREIGN KEY (permiso_id) REFERENCES Permiso(id)\n" +
        ");\n" +
        "\n" +
        "-- Tabla: UsuarioRol\n" +
        "CREATE TABLE UsuarioRol (\n" +
        "    usuario_id INT,\n" +
        "    rol_id INT,\n" +
        "    PRIMARY KEY (usuario_id, rol_id),\n" +
        "    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),\n" +
        "    FOREIGN KEY (rol_id) REFERENCES Rol(id)\n" +
        ");";

        String[] statements = sql.split(";");
        Mono<Void> mono = Mono.empty();

        for (String statement : statements) {
            if (!statement.trim().isEmpty()) {
                mono = mono.then(databaseClient.sql(statement.trim()).fetch().rowsUpdated().then());
            }
        }

        return mono;
    }
}


*/
