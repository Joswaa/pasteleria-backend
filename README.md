# Pasteleria API - Spring Boot + Swagger + AWS
Integrantes:
-Joshua Martinez
-Ignacia cavedo

Backend REST desarrollado con **Spring Boot 3** para exponer el catálogo de productos de la pastelería **Mil Sabores**.  
La API está desplegada en una instancia **AWS EC2** y es consumida por:

- Una aplicación **Android** mediante Retrofit.
- El **frontend web** de la pastelería, desarrollado con **React**, que usa esta API para el catálogo, el carrito y el inicio de sesión.

## Tecnologías

- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Security (configurada en modo abierto para la demo)
- Springdoc OpenAPI (Swagger UI)
- Maven
- Despliegue en AWS EC2 (Amazon Linux)

## Endpoints principales

Controlador: `ProductController`

- `GET /products`
  - Devuelve la lista completa de productos (`List<ProductDto>`).
- `GET /products/{id}`
  - Devuelve el detalle de un producto por id.
- `POST /products`
  - Crea un nuevo producto en memoria (para pruebas vía Swagger).
- `PUT /products/{id}`
  - Actualiza un producto existente.
- `DELETE /products/{id}`
  - Elimina un producto de la lista.

Modelo transferido:

```java
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageUrl;
    // getters y setters
}
```
Los productos se almacenan en una lista en memoria en ProductService, suficiente para la demo y para probar el consumo desde Android.

Seguridad
Se usa una configuración simple que desactiva CSRF y permite todas las peticiones (ideal para desarrollo y pruebas):
```
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
```
Swagger / OpenAPI
Se integra springdoc-openapi-starter-webmvc-ui en el pom.xml:
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>

Rutas de documentación:

JSON OpenAPI: `http://IP_EC2:9090/v3/api-docs`

Swagger UI: `http://IP_EC2:9090/swagger-ui/index.html`

Desde Swagger se pueden probar:

Listado de productos (GET).

Creación/edición/borrado de productos (POST/PUT/DELETE).

Ver el schema de `ProductDto`
## Despliegue en AWS EC2
1-Compilar el proyecto:
`mvn clean package -DskipTests`
2-Copiar el JAR a EC2 (desde la máquina local):
`scp -i spring-key.pem target/pasteleria-api-0.0.1-SNAPSHOT.jar ec2-user@EC2_PUBLIC_IP:~/app/app.jar`
3-Conectarse por SSH y ejecutar:
```
ssh -i spring-key.pem ec2-user@EC2_PUBLIC_IP
cd ~/app
java -jar app.jar --server.port=9090
```
Probar desde el navegador:
-`http://EC2_PUBLIC_IP:9090/products`
-`http://EC2_PUBLIC_IP:9090/products`
Asegurarse de que el Security Group de la instancia EC2 tenga abierto el puerto 9090 para HTTP.
## Ejecución local
Para correr la API localmente (sin AWS):
-`mvn spring-boot:run`(cmd de la aplicacion)
-`http://localhost:9090/swagger-ui/index.html`
Estos comandos se ejecutan en su mayoria en la cmd de su PC

## 📚 Licencia

Proyecto académico desarrollado como parte de las asignaturas de **Desarrollo de Aplicaciones Móviles** y **Desarrollo de Aplicaciones Web**, integrando un backend en Spring Boot con clientes Android y web (React).
