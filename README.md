# AppEntrenamiento 🏋️

Aplicación web para acompañar la actividad física de los usuarios mediante la generación de rutinas de entrenamiento personalizadas, el seguimiento de su progreso y el acceso a recursos relacionados con ejercicio y nutrición. La plataforma permite registrar información física, consultar rutinas semanales, localizar espacios para entrenar y gestionar distintos tipos de membresía.

```

![Logo de AppEntrenamiento](src/main/webapp/resources/core/images/logo.png)

## Índice
* [Funcionalidades principales](#funcionalidades-principales)
* [Objetivo](#objetivo)
* [Screenshots](#screenshots)
* [Lenguajes y tecnologías](#-lenguajes-y-tecnologías)
* [Cómo ejecutar](#cómo-ejecutar)
* [Pruebas](#pruebas)
* [Autora](#autora)

## Funcionalidades principales
- **Registro e inicio de sesión:** creación de cuentas, autenticación y cierre de sesión.
- **Perfil y aptitud física:** carga de datos personales, objetivos y preferencias de entrenamiento.
- **Rutinas personalizadas:** generación y visualización de rutinas semanales y diarias según los tipos de entrenamiento seleccionados y el tiempo disponible.
- **Seguimiento de ejercicios:** identificación de actividades realizadas y pendientes.
- **Progreso físico:** registro de peso, cálculo de IMC y consulta de estadísticas de entrenamiento.
- **Búsqueda de lugares:** consulta de espacios para ejercitarse y filtrado por actividad y distancia a la ubicación del usuario.
- **Membresías:** diferentes niveles de acceso a funcionalidades, con integración de Mercado Pago para procesar pagos.
- **Tips y nutrición:** consulta de artículos y recomendaciones organizadas por tipo de entrenamiento, disponible según el plan.
- **Exportación de rutinas:** descarga de rutinas en formato Excel.

> Algunas secciones, como estadísticas y artículos de nutrición, requieren un nivel de membresía determinado.

## Objetivo
El proyecto se desarrolló como parte de **Taller Web I** para aplicar conceptos de desarrollo web con Java y el patrón **MVC (Modelo-Vista-Controlador)**, separando la presentación, la lógica de negocio y la persistencia de datos.

También permite trabajar con autenticación, generación de rutinas, bases de datos relacionales, integración con servicios externos, pruebas automatizadas y herramientas de construcción y gestión de dependencias.

## Screenshots
El repositorio contiene recursos gráficos de la aplicación, incluido su logo. Se pueden incorporar capturas reales de las pantallas de inicio, rutinas, perfil y estadísticas en esta sección.

## 🛠 Lenguajes y tecnologías
- **Backend:** Java 11, Spring MVC 5.2.22, Hibernate 5.4.24, Spring Data JPA.
- **Frontend:** HTML, CSS, JavaScript, Thymeleaf 3.0.15, Bootstrap 5.2.0.
- **Base de datos:** MySQL 8, con HSQLDB como dependencia del proyecto.
- **Construcción y servidor:** Maven, Jetty 9.4.45.
- **Integraciones:** SDK de Mercado Pago, Apache POI para generar archivos Excel.
- **Testing:** JUnit 5, Mockito, Hamcrest, Spring Test, Playwright y pruebas de integración.
- **Herramientas:** IntelliJ IDEA, Git y GitHub.

## Cómo ejecutar

### Requisitos
- JDK 11.
- Maven.
- MySQL en ejecución.
- Puerto 8080 disponible para Jetty.

### 1. Clonar el repositorio
```bash
git clone https://github.com/Karen-nina/AppEntrenamiento.git
cd AppEntrenamiento
```

### 2. Configurar la base de datos
Crear una base de datos MySQL denominada `taller_web`:

```sql
CREATE DATABASE taller_web;
```

Revisar el archivo `src/main/resources/application.properties` y adaptar las credenciales a la instalación local:

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/taller_web
spring.datasource.username=root
spring.datasource.password=
```

**Atención:** la configuración actual de Hibernate utiliza `hibernate.hbm2ddl.auto=create`, por lo que el esquema puede recrearse al iniciar la aplicación y eliminar datos existentes. Utilizar una base de datos de desarrollo. La inicialización incluye el script `src/main/resources/data.sql`.

### 3. Iniciar la aplicación
Ejecutar en la raíz del proyecto:

```bash
mvn clean jetty:run
```

Abrir en el navegador:

```text
http://localhost:8080/login
```

La configuración de Jetty utiliza el contexto raíz (`/`). Si se cambia la configuración del servidor, ajustar la dirección de acceso.

### 4. Integraciones
Las funcionalidades relacionadas con pagos requieren configurar las credenciales correspondientes de Mercado Pago. No publicar credenciales ni claves privadas en el repositorio.

## Pruebas
El proyecto incluye pruebas unitarias, de integración y pruebas de interfaz de punta a punta.

Para ejecutar la suite configurada en Maven:

```bash
mvn test
```

Para ejecutar la prueba de inicio de sesión con Playwright, iniciar primero la aplicación:

```bash
mvn clean jetty:run
```

Y en otra terminal:

```bash
mvn test -Dtest=VistaLoginE2E
```

> Las pruebas E2E pueden requerir la instalación de los navegadores utilizados por Playwright y una base de datos local preparada.

## Autora
- **Karen Nina Coela** — [GitHub](https://github.com/Karen-nina)

---

Proyecto basado en una plantilla académica de Taller Web I con Maven, Spring MVC y Thymeleaf. Documentación adaptada a las funcionalidades y dependencias presentes en este repositorio.
