# Sistema de Inventario

Aplicación web desarrollada con Spring Boot para la administración de inventario de una empresa.
El sistema permite gestionar productos, entradas y salidas de inventario, historial de movimientos y control de acceso mediante roles.

---

# Tecnologías utilizadas

## IDE utilizado

- IntelliJ IDEA

## Lenguaje de programación

- Java 17

## Framework utilizado

- Spring Boot 3

## Motor de base de datos (DBMS)

- MySQL Server 8
- MySQL Workbench

## Dependencias principales

- Spring Web
- Srping Data JPA
- Spring Security
- Thymeleaf
- MySQL Driver
- Lombok

---

# Funcionalidades del sistema

## Inventario

- Visualización de productos
- Registro de nuevos productos
- Activación y baja lógica de productos
- Entrada de inventario
- Salida de inventario
- Validación de stock

## Historial

- Registro de entradas y salidas
- Registro del usuario que realizó el movimiento
- Fecha y hora del movimiento
- Filtro por tipo de movimiento

## Seguridad

- Inicio de sesión
- Roles:
  - ADMINISTRADOR
  - ALMACENISTA
- Restricción de persmisos por rol
- Cierre de sesión

---

# Estructura del proyecto

```text
com.prueba.inventario
│
├── config
│   └── SecurityConfig.java
│
├── controller
│   └── ProductoController.java
│
├── entity
│   ├── HistoricoMovimiento.java
│   ├── Producto.java
│   ├── Rol.java
│   └── Usuario.java
│
├── repository
│   ├── HistoricoMovimientoRepository.java
│   ├── ProductoRepository.java
│   ├── RolRepository.java
│   └── UsuarioRepository.java
│
├── service
│   ├── HistoricoMovimientoService.java
│   └── ProductoService.java
│
├── service.impl
│   ├── HistoricoMovimientoServiceImpl.java
│   └── ProductoServiceImpl.java
│
├── service.security
│   └── UsuarioSecurityService.java
│
└── InventarioApplication.java
```

# Plantillas HTML

Ubicadas en:

```text
src/main/resources/templates
```

## Archivos

`inventario.html`
`nuevo-producto.html`
`entrada-producto.html`
`salida-producto.html`
`historial.html`

---

# Configuración de base de datos

El archivo

```text
src/main/resources/application.properties
```

debe configurarse con los datos locales de MySQL.

## Ejemplo de configuración

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventario
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
```

> **Nota:** Por seguridad, el usuario y contraseña de MySQL no se incluyen en el repositorio.

---

# Pasos para ejecutar la aplicación

## 1. Clonar el repositorio

```bash
git clone https://github.com/monica-rincongallardo/inventario.git
```

## 2. Abrir el proyecto

Abrir el proyecto en ItelliJ IDEA

## 3. Configurar `application.properties`

Modificar:

```text
src/main/resources/application.properties
```

y colocar:

- Usuario de MySQL
- Contraseña de MySQL

## 4. Ejecutar el script SQL en MySQL

Ejecutar el script proporcionado para crear la base de datos, las tablas y registros iniciales.
Se encuentra dentro de la carpeta:

```text
SCRIPTS
```

se encuentra el archivo:

```text
inventario.sql
```

El script debe ejecutarse en MySQL Workbench para crear:

- tablas
- relaciones
- roles
- registros iniciales del sistema

## 5. Ejecutar la aplicación

Ejecutar la clase:

```text
InventarioApplication
```
Ubicado en:
```text
src/main/java/com/prueba/inventario
```

## 6. Abrir el navegador

Entrar a:

```text
http://localhost:8080/inventario
```

---

# Usuario y Roles

| Rol           | Permisos                                   |
| ------------- | ------------------------------------------ |
| ADMINISTRADOR | Gestión de productos, entradas e historial |
| ALMACENISTA   | Salidas de inventario                      |

### Creado por:

Mónica Rincón Gallardo Nava
