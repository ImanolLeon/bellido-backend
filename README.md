# BellidoMap Backend

API REST para la gestión académica del Colegio María Parado de Bellido. Desarrollada con Spring Boot 4, Spring Security + JWT, JPA/Hibernate y PostgreSQL.

<img width="1332" height="597" alt="image" src="https://github.com/user-attachments/assets/9d0612d8-7d38-4fc1-8962-aeef2511a2ac" />

<img width="1321" height="600" alt="image" src="https://github.com/user-attachments/assets/2e83f4ee-4794-4e72-ac42-900b3a96c66b" />



## Tecnologías

- [Java](https://openjdk.org/) 25
- [Spring Boot](https://spring.io/projects/spring-boot) 4.1.1
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [JWT (jjwt)](https://github.com/jwtk/jjwt)
- [PostgreSQL](https://www.postgresql.org/)
- [Lombok](https://projectlombok.org/)
- [Maven](https://maven.apache.org/)

## Requisitos

- JDK 25
- Maven 3.9+
- PostgreSQL 14+

## Variables de entorno

Crear un archivo `.env` en la raíz del proyecto con las siguientes variables:

```env
DB_USERNAME=username
DB_PASSWORD=password
DB_URL=jdbc:postgresql://localhost:5432/bellido_map
JWT_SECRET=claveSecretaSuperSeguraParaJwtBellidoMap2026Secret
JWT_EXPIRATION=86400000
```

- `JWT_SECRET` debe tener al menos 32 caracteres. Si no se define, se usan valores por defecto.
- `JWT_EXPIRATION` está en milisegundos (por defecto 24 horas).

## Ejecución

1. Crear la base de datos `bellido_map` en PostgreSQL
2. Configurar las variables de entorno
3. Ejecutar la aplicación:

```bash
./mvnw spring-boot:run
```

La API se levantará en `http://localhost:8080`.

## Autenticación

- `POST /auth/login` - Iniciar sesión. Requiere `dni` y `password`. Retorna un token JWT.

Ejemplo:

```json
{
  "dni": "61230119",
  "password": "123456"
}
```

El `DataSeeder` crea un usuario administrador por defecto con `dni: 61230119` y `password: 123456` al iniciar la app.

## Estructura

```text
src/main/java/com/example/BelidoMap/
├── config/      # Configuración y DataSeeder
├── controller/  # Controladores REST
├── dto/         # Request/Response DTOs y mappers
├── entities/    # Entidades JPA
├── exception/   # Manejo de excepciones
├── repository/  # Repositorios JPA
├── security/    # Configuración JWT y Spring Security
└── services/    # Lógica de negocio
```

## Colección de pruebas

Existe una colección para Bruno/OpenCollection en `BellidoMapCollection/` con las peticiones para probar los endpoints.
