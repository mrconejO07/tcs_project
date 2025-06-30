# 💻 TCS PROJECT – Ejercicio Técnico Backend Java

Esta solución implementa una arquitectura de microservicios desacoplada, basada en principios de diseño orientado al dominio, buenas prácticas de ingeniería de software, separación de responsabilidades, y preparada para la escalabilidad y el despliegue mediante contenedores.

---

## 🧩 Arquitectura General

- **`config-service`**: Servidor de configuración centralizado utilizando Spring Cloud Config Server. Carga propiedades desde un repositorio remoto en Git.
- **`gateway-service`**: API Gateway implementado con Spring Cloud Gateway. Se encarga de enrutar las peticiones a los microservicios correspondientes.
- **`cliente-service`**: Microservicio responsable de la gestión de personas y clientes.
- **`cuenta-service`**: Microservicio encargado de la gestión de cuentas bancarias y sus movimientos.

---

## ✅ Requisitos funcionales implementados

| Código | Descripción |
|--------|-------------|
| F1     | CRUD completo para Cliente, Cuenta y Movimiento *(ver nota profesional sobre Movimiento)* |
| F2     | Registro de movimientos con actualización automática del saldo de la cuenta |
| F3     | Validación de saldo insuficiente con mensaje de error: `"Saldo no disponible"` |
| F4     | Generación de reporte de estado de cuenta por cliente y rango de fechas |
| F5     | Pruebas unitarias para la entidad `Cliente` |
| F6     | Pruebas de integración entre capas (servicios/repositorios/controladores) |
| F7     | Despliegue containerizado mediante Docker con imagen multi-stage y separación de perfiles |

---

## ⚠️ Consideraciones profesionales

### 🔒 Inmutabilidad de la entidad `Movimiento`

En contextos financieros, **los movimientos no deben modificarse una vez creados**. Aunque se solicita implementar operaciones CRUD, **la operación de actualización (`PUT`) ha sido omitida deliberadamente** para mantener la integridad y trazabilidad de la información.

> En casos reales, un movimiento incorrecto debe compensarse mediante otro movimiento inverso.

---

### 💡 Sobre el campo `saldo` en `Cuenta`

La entidad `Cuenta` incluye un campo `saldo`, que representa el **saldo actual disponible**.

- Se actualiza automáticamente al registrar nuevos movimientos.
- Se mantiene como único campo de saldo para evitar inconsistencias.
- Si se requiere registrar un valor inmutable al momento de apertura, se puede agregar un campo adicional `saldoInicial`.

---

## ⚙️ Tecnologías utilizadas

- Java 17
- Spring Boot 3.5.x
- Spring Data JPA
- PostgreSQL
- Spring Cloud Config Server
- Spring Cloud Gateway
- Spring Cloud LoadBalancer
- MapStruct (mapeo entre entidades y DTOs)
- JUnit + Mockito (pruebas unitarias)
- Docker / Docker Compose

---

## 🧪 Ejecución local

```bash
# Clona el repositorio principal
git clone https://github.com/mrconejO07/tcs_project.git
cd tcs_project

# Construye cada microservicio
cd microservicios/cliente-service
./mvnw clean package -DskipTests

cd ../cuenta-service
./mvnw clean package -DskipTests

cd ../config-service
./mvnw clean package -DskipTests

cd ../gateway-service
./mvnw clean package -DskipTests

# Volver al root y levantar los servicios con Docker Compose
cd ../../
docker-compose up --build

---

## 🚀 Próximos pasos y mejoras

Añadir seguridad JWT o OAuth2 para proteger las APIs.

Automatizar el despliegue con pipelines CI/CD.

Despliegue automatizado con pipelines CI/CD (GitHub Actions, GitLab CI o Jenkins).

