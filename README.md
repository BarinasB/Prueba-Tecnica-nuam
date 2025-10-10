# 🧩 Product API — Prueba Técnica

API REST desarrollada en **Spring Boot** para la gestión de productos.  
Incluye conexión a **SQL Server** y despliegue mediante **Docker Compose**.

---

## 🚀 Tecnologías principales
- Java 17  
- Spring Boot 3 (Web, JPA, Validation)  
- SQL Server 2022  
- Maven  
- Docker y Docker Compose

---

## 📂 Estructura del proyecto

```plaintext
C:.
├───.mvn
│   └───wrapper
├───sql
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───example
│   │   │           └───productapi
│   │   │               ├───controller
│   │   │               ├───dto
│   │   │               ├───exception
│   │   │               ├───model
│   │   │               ├───repository
│   │   │               └───service
│   │   └───resources
│   │       ├───static
│   │       └───templates
│   └───test
│       └───java
│           └───com
│               └───example
│                   └───productapi

```

## ⚙️ Comandos básicos de ejecución
```plaintext
# Detener y eliminar contenedores
docker compose down

# Construir y levantar contenedores
docker compose up -d 

