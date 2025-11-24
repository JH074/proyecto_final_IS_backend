# Aplicación de sistema de reservas de canchas [BACKEND]

Este es el backend del sistema de reservas de canchas deportivas. Está desarrollado en Java con Spring Boot y expone una API REST que permite gestionar usuarios, canchas, reservas, zonas, lugares, métodos de pago y más. 

El sistema implementa una **arquitectura N-Capas** (Controladores, Servicios, Repositorios, Entidades, DTOs) para una mejor organización y mantenimiento del código.


## Tecnologías utilizadas

- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
- PostgreSQL
- Lombok
- Postman

---

## Arquitectura N-Capas

El proyecto está estructurado en capas:

- **Controladores:** Manejan las solicitudes HTTP.
- **Servicios:** Lógica de negocio.
- **Repositorios:** Acceso a datos con JPA.
- **Entidades:** Representan las tablas de la base de datos.
- **DTOs:** Intercambio de datos entre capas.
- **Security:** Seguridad JWT y configuración de acceso.
- **Seeders:** Carga de datos por defecto.

---

## Estructura del proyecto

```bash
src/main/java/org/ncapas/canchitas/
├── Controllers/
├── DTOs/
│   ├── request/
│   └── response/
├── entities/
├── exception/
├── repositories/
├── Scheduler/
├── security/
│   └── config/
├── Service/
├── utils/mappers/
├── CanchitasApplication
├── DataSeederMetodoPago
├── ...

```

## Requisitos previos 

- Java 21
- Maven
- PostgreSQL
- Docker / Docker Compose

## Instalación y ejecución

1. Clonar el repositorio 

```bash
  git clone https://github.com/JH074/proyecto_final_IS_backend.git
  cd proyecto_final_IS_backend
```

2. Configurar la base de datos en application.yml:

```bash
  spring:
  application:
    name: canchitas


  datasource:
    url: jdbc:postgresql://localhost:5432/canchitas_management
    username: postgres
    password: {contraseña}

  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update

```

3. Ejecutar la aplicación

Con Docker
```bash
  cd Canchitas
  docker-compose up --build -d
```

Accedé a la API en: http://localhost:8080

## Autenticación y uso de Token JWT 

La mayoría de los endpoints requieren autenticación mediante token JWT. Para probarlos desde Postman, seguí estos pasos:

1. Iniciar Sesión 
```bash 
  POST /api/auth/login
```

2. Body:
```bash 
  {
    "correo": "usuario@example.com",
    "contrasena": "123456"
  }
```
3. Obtener el token:
```bash 
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    ...
  }
```

Al hacer las peticiones:

* Ir a la pestaña Authorization.
* Tipo: Bearer Token.
* Pegar el token en el campo.
