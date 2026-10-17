# AppEntrenamiento

AppEntrenamiento es una aplicación web para organizar y acompañar el entrenamiento físico. Permite registrar usuarios, recopilar información sobre su aptitud física y preferencias, generar rutinas semanales, consultar ejercicios y llevar un seguimiento personal. También incorpora búsqueda de lugares para realizar actividades, información sobre nutrición y membresías con integración de pagos.


![Logo de AppEntrenamiento](src/main/webapp/resources/core/images/logo.png)

## Índice
- [Funcionalidades principales](#funcionalidades-principales)
- [Objetivo](#objetivo)
- [Screenshots](#screenshots)
- [Lenguajes y tecnologías](#-lenguajes-y-tecnologías)
- [Cómo ejecutar](#cómo-ejecutar)
- [Autora](#autora)

## Funcionalidades principales
- **Registro e inicio de sesión:** creación de usuarios y acceso a la aplicación.
- **Perfil y aptitud física:** recopilación de datos físicos, preferencias, tipos de entrenamiento y disponibilidad para entrenar.
- **Rutinas de entrenamiento:** generación y visualización de rutinas semanales con ejercicios organizados por día.
- **Seguimiento de peso:** registro y consulta de datos vinculados al progreso personal.
- **Exploración de lugares:** búsqueda de espacios para realizar actividades físicas, con filtros por actividad y distancia y actualización de ubicación.
- **Tips y nutrición:** sección de artículos y recomendaciones informativas.
- **Membresías:** gestión de planes gratuito, intermedio y premium, con integración de Mercado Pago para los planes pagos.
- **Exportación de rutinas:** generación y descarga de rutinas en formato Excel (`.xlsx`).

## Objetivo
El proyecto se desarrolló para aplicar los conceptos de desarrollo web con **Java y Spring MVC**, utilizando una arquitectura organizada en capas y el patrón **Modelo–Vista–Controlador (MVC)**. Integra persistencia relacional con **Hibernate y MySQL**, renderizado de vistas con **Thymeleaf**, pruebas automatizadas y servicios externos, con el objetivo de ofrecer una plataforma de acompañamiento para personas que desean planificar y registrar sus entrenamientos.

## Screenshots

El repositorio contiene las vistas `home.html`, `login.html`, `formularioAptitudFisica.html`, `misRutinas.html`, `perfil.html`, `mapaBuscador.html` y `tipsYNutricion.html`, pero no incluye una carpeta identificada de capturas de pantalla listas para documentar. Se pueden agregar capturas reales en `docs/screenshots/` y vincularlas aquí:

<!--
![Inicio](docs/screenshots/inicio.png)
![Rutinas](docs/screenshots/rutinas.png)
![Mapa de actividades](docs/screenshots/mapa.png)
-->

## 🛠 Lenguajes y tecnologías

**Backend:** Java 11, Spring Web MVC 5, Spring Data JPA, Hibernate 5, Maven.

**Frontend:** HTML, CSS, JavaScript, Thymeleaf y Bootstrap.

**Base de datos:** MySQL 8 (conector JDBC); HSQLDB incluido entre las dependencias del proyecto.

**Integraciones:** Mercado Pago SDK y Apache POI para exportar archivos Excel.

**Testing:** JUnit 5, Mockito, Hamcrest, Spring Test y Playwright.

**Arquitectura:** MVC, con paquetes de presentación, dominio e infraestructura.

## Cómo ejecutar

### Requisitos
- JDK 11.
- Maven.
- MySQL instalado y en ejecución.
- Un entorno capaz de ejecutar aplicaciones web Java; el proyecto incluye el plugin Maven de Jetty.

### 1. Clonar el repositorio

```bash
git clone https://github.com/Karen-nina/AppEntrenamiento.git
cd AppEntrenamiento
```

### 2. Configurar la base de datos

Crear la base de datos local utilizada por la aplicación:

```sql
CREATE DATABASE taller_web;
```

Revisar `src/main/resources/application.properties` y ajustar la conexión a tu instancia de MySQL:

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/taller_web
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
```

> **Importante:** `HibernateConfig.java` incluye `hibernate.hbm2ddl.auto=create`, que puede recrear el esquema al iniciar y eliminar datos existentes. Utilizá una base de datos de desarrollo. La configuración también ejecuta el archivo `src/main/resources/data.sql` durante la inicialización.

### 3. Compilar y ejecutar

```bash
mvn clean package
mvn jetty:run
```

Abrir la aplicación en el navegador (con el puerto predeterminado de Jetty, salvo configuración local diferente):

```text
http://localhost:8080/
```

Para ejecutar las pruebas automatizadas:

```bash
mvn test
```

> Algunas funcionalidades, especialmente los pagos mediante Mercado Pago, pueden requerir credenciales y configuración adicional para funcionar correctamente. Los comandos reflejan el empaquetado WAR y el plugin Jetty declarados en `pom.xml`; la ejecución completa depende de que las dependencias y servicios externos estén configurados.

## Autora
- **Karen Nina Coela** — [GitHub: Karen-nina](https://github.com/Karen-nina)


