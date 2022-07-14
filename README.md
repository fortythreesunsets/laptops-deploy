## Spring Boot

DEPENDENCIAS

Starters para persistencia:
* Spring Web
* H2

Starters para web:
* Spring Data JPA
* Spring Boot DevTools

Starter para documentación:
* Springfox (Swagger)

Para resolver errores agregar:
* Anotación `@EnableWebMvc` en la clase RestDataJpaApplication
  y el @Bean public `InternalResourceViewResolver defaultViewResolver() {return new InternalResourceViewResolver();}` en SwaggerConfig
* O sólo agregar `spring.mvc.pathmatch.matching-strategy=ant-path-matcher` en application.properties


Aplicación API REST con acceso a base de datos H2 para persistir la información.

El acceso se puede realizar desde Postman o navegador.

Orden de elementos a crear:

## Entidad Laptop
1. Laptop(clase @Entity)
2. LaptopRepository (interface @Repository)
3. LaptopController (clase @RestController)
    1. Buscar todas las laptops
    2. Buscar una sola laptop
    3. Crear una laptop
    4. Actualizar una laptop
    5. Borrar una laptop
    6. Borrar todas las laptops

## Despliegue de apps Spring Boot en heroku

1. 1. Generar archivo `.gitignore` en https://www.toptal.com/developers/gitignore/ con archivos Java, Maven e IntelliJ y agregar `.idea` en `#Generated Files`
2. Crear archivo `system.properties` en la raíz del proyecto con el contenido:
   `java.runtime.version=17`
3. Agregar al pom.xml:
```
<plugin>
   <groupId>org.apache.maven.plugins</groupId>
   <artifactId>maven-resources-plugin</artifactId>
   <version>3.1.0</version>
</plugin>
```
3. Tener configurado git en la computadora:

`git config --global user.name "fortythreesunsets"`
`git config --global user-email fortythreesunsets@example.com`

2. Crear archivo Procfile sin extensión con el contenido:

`web: java -Dserver.port=$PORT $JAVA_OPTS -jar target/rest-data-jpa-0.0.1-SNAPSHOT.jar`
4. Subir el proyecto a GitHub desde IntelliJ IDEA desde la opción VSC > Share Project on GitHub
5. Desde Heroku, crear app y elegir método GitHub y buscar el repositorio
6. Habilitar deploy automático y ejecutar Deploy (manual la primera vez)

## Spring Security
1. Agregar dependencias al `pom.xml`:
```
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
   <groupId>org.springframework.security</groupId>
   <artifactId>spring-security-test</artifactId>
   <scope>test</scope>
</dependency>
```