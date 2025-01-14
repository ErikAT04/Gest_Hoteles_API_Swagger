DROP DATABASE IF EXISTS GEST_HOTELES;
CREATE DATABASE GEST_HOTELES;
USE GEST_HOTELES;

CREATE TABLE HOTEL(
                      id INTEGER PRIMARY KEY AUTO_INCREMENT,
                      nombre VARCHAR(255) NOT NULL,
                      descripcion VARCHAR(255),
                      categoria VARCHAR(255),
                      tiene_piscina BOOLEAN NOT NULL,
                      localidad VARCHAR(255)
);

CREATE TABLE HABITACION(
                           id INTEGER PRIMARY KEY AUTO_INCREMENT,
                           tamanio INTEGER NOT NULL,
                           una_persona BOOLEAN NOT NULL,
                           precio_noche DOUBLE NOT NULL,
                           incluye_desayuno BOOLEAN NOT NULL,
                           ocupada BOOLEAN NOT NULL,
                           hotel INTEGER NOT NULL REFERENCES HOTEL(id) ON DELETE CASCADE
);

INSERT INTO HOTEL(nombre, descripcion, categoria, tiene_piscina, localidad) values ('NH', 'Hotel NH en Valladolid', '5E', false, 'Valladolid');
INSERT INTO HOTEL(nombre, descripcion, categoria, tiene_piscina, localidad) values ('Zentral', 'Hotel cerca de la estacion de buses de Valladolid', '4E', false, 'Valladolid');
INSERT INTO HOTEL(nombre, descripcion, categoria, tiene_piscina, localidad) values ('Imperial', 'Hotel en la Plaza Mayor de Valladolid', '2E', false, 'Valladolid');
INSERT INTO HOTEL(nombre, descripcion, categoria, tiene_piscina, localidad) values ('Ilunion Alcalá Norte', 'Hotel en el norte de Madrid, a 10 minutos del IFEMA', '4E', false, 'Madrid');
INSERT INTO HOTEL(nombre, descripcion, categoria, tiene_piscina, localidad) values ('La Lonja', 'Hotel cerca del mercado central de Alicante', '2E', false, 'Alicante');

INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (1, 30, false, 120.0, true, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (1, 20, false, 100.0, true, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (1, 50, false, 150.0, true, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (2, 30, true, 90.0, true, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (2, 15, true, 70.0, false, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (2, 20, false, 80.0, true, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (3, 10, true, 50.0, false, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (3, 25, false, 90.0, true, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (3, 12, false, 60.0, false, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (4, 23, true, 110.0, true, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (4, 18, false, 95.0, false, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (4, 22, false, 105.0, true, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (5, 15, true, 80.0, false, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (5, 20, false, 85.0, true, false);
INSERT INTO HABITACION(hotel, tamanio, una_persona, precio_noche, incluye_desayuno, ocupada) values (5, 25, true, 90.0, true, false);