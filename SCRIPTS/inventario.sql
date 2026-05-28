-- crear base de datos
CREATE DATABASE inventario;
USE inventario;

-- tabla roles
CREATE TABLE roles (
	idRol INT PRIMARY KEY AUTO_INCREMENT,
    nombreRol VARCHAR(50) NOT NULL
);

-- tabla usuarios
CREATE TABLE usuarios (
	idUsuario INT(6) PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    contrasena VARCHAR(25) NOT NULL,
    idRol INT NOT NULL,
    estatus INT(1) NOT NULL,
    
    FOREIGN KEY (idRol) REFERENCES roles(idRol)
);

-- tabla productos
CREATE TABLE productos (
	idProducto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    estatus INT(1) NOT NULL
);

-- tabla histórico de movimientos
CREATE TABLE historico_movimientos (
	idMovimiento INT PRIMARY KEY AUTO_INCREMENT,
    idProducto INT NOT NULL,
    idUsuario INT NOT NULL,
    tipoMovimiento VARCHAR(20) NOT NULL,
    cantidad INT NOT NULL,
    fechaMovimiento DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto),
    FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
);

-- añadir rol administrador
INSERT INTO roles(nombreRol)
VALUES ('ADMINISTRADOR');
SELECT * FROM roles;

-- añadir usuario administrador 
INSERT INTO usuarios(nombre, correo, contrasena, idRol, estatus)
VALUES ('Administrador', 'admin@inventario.com', '1234', 1, 1);
SELECT * FROM usuarios;

-- actualizar rol administador
UPDATE roles
SET nombreRol = 'ROLE_ADMINISTRADOR'
WHERE idRol = 1;

-- añadir rol almacenista
INSERT INTO roles(nombreRol)
VALUES ('ROLE_ALMACENISTA');

-- añadir usuario almacenista
INSERT INTO usuarios(nombre, correo, contrasena, idRol, estatus)
VALUES ('Almacenista', 'almacen@inventario.com', '1234', 2, 1);
SELECT * FROM usuarios;
