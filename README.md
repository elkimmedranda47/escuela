# Sistema Escolar

Este es un proyecto de demostración que utiliza Spring Boot con JPA para crear una aplicación RESTful. El proyecto incluye seguridad basada en JWT, validación de datos, y conexión a una base de datos PostgreSQL.

## Tecnologías Utilizadas

- **Spring Boot**: Framework para desarrollar aplicaciones Java.
- **Spring Security**: Para manejar la autenticación y autorización.
- **JWT (JSON Web Tokens)**: Para la autenticación basada en tokens.
- **Spring Data JPA**: Para la persistencia de datos.
- **PostgreSQL**: Base de datos relacional.
- **Lombok**: Para reducir el código boilerplate.
- **ModelMapper**: Para mapear objetos entre diferentes capas de la aplicación.
- **Jackson**: Para la serialización y deserialización de JSON.
- **OpenAPI**: Para la documentación de la API.

## Requisitos Previos

- **Java 21**: Asegúrate de tener instalado Java 21.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.
- **PostgreSQL**: Debes tener una instancia de PostgreSQL corriendo.

## Configuración

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/elkimmedranda47/escuela
   cd escuela
Configura la base de datos:

Crea una base de datos en PostgreSQL.

Configura las credenciales de la base de datos en el archivo application.properties:

properties
Copy
spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
Compila y ejecuta la aplicación:

bash
Copy
mvn clean install
mvn spring-boot:run
Accede a la API:

La aplicación estará disponible en http://localhost:8080.

Puedes acceder a la documentación de la API en http://localhost:8080/swagger-ui.html.

Estructura del Proyecto
src/main/java: Contiene el código fuente de la aplicación.

com.restApi: Paquete principal.

controller: Controladores REST.

service: Lógica de negocio.

repository: Repositorios de datos.

model: Entidades de la base de datos.

security: Configuración de seguridad.

dto: Objetos de transferencia de datos.

config: Configuraciones adicionales.

src/main/resources: Contiene archivos de configuración y recursos.

application.properties: Configuración de la aplicación.

src/test: Contiene las pruebas unitarias y de integración.

Dependencias Principales
Spring Boot Starter Web: Para aplicaciones web RESTful.

Spring Boot Starter Security: Para seguridad.

Java JWT: Para manejar JWT.

Spring Boot Starter Data JPA: Para la persistencia de datos.

PostgreSQL Driver: Conexión a PostgreSQL.

Lombok: Para reducir el código boilerplate.

ModelMapper: Para mapeo de objetos.

Jackson Databind: Para manejo de JSON.

Ejecución de Pruebas
Para ejecutar las pruebas, utiliza el siguiente comando:

bash
Copy
mvn test
Contribución
Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

Haz un fork del repositorio.

Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).

Realiza tus cambios y haz commit (git commit -am 'Añade nueva funcionalidad').

Haz push a la rama (git push origin feature/nueva-funcionalidad).

Abre un Pull Request.

Licencia
Este proyecto está bajo la licencia MIT. Consulta el archivo LICENSE para más detalles.

Este README proporciona una visión general del proyecto y cómo configurarlo y ejecutarlo.
