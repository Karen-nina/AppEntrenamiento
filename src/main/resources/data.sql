-- Insertar datos en la tabla aptitudes_fisicas con las opciones permitidas
INSERT INTO aptitudFisica (altura, estadoFisico, horasEntrenamiento, peso)
VALUES (1.75,  'entrenado', 2.0, 70.0),
       (1.80,  'entrenado', 1.5, 75.0),
       (1.65, 'intermedio', 1.0, 60.0);
-- Insertar datos de prueba en Usuario
INSERT INTO usuario ( activo, apellido, nombre, email, latitud, longitud, password, rol, aptitudFisica_id)
VALUES ( TRUE, 'Garcia', 'Juan', 'ni@ni.com', -34.648927, -58.576088, '123', 'user', 1),
       ( TRUE, 'Lopez', 'Maria', 'da@da.com', -34.644619, -58.571625, '123', 'user', 2),
       (TRUE, 'Perez', 'Carlos', 'le@le.com', -34.640989, -58.579419, '123', 'user', 3);

UPDATE aptitudFisica
SET usuario_id = 1
WHERE id = 1;
UPDATE aptitudFisica
SET usuario_id = 2
WHERE id = 2;
UPDATE aptitudFisica
SET usuario_id = 3
WHERE id = 3;

UPDATE usuario
SET aptitudFisica_id = 1
WHERE id = 1;
UPDATE usuario
SET aptitudFisica_id = 2
WHERE id = 2;
UPDATE usuario
SET aptitudFisica_id = 3
WHERE id = 3;


INSERT INTO TipoEntrenamiento (id, nombre)
VALUES
    (1, 'Musculacion'),
    (2, 'Calistenia'),
    (3, 'Cardio');


INSERT INTO AptitudFisicaTipoEntrenamiento (aptitudFisica_id, tipoEntrenamiento_id,dias)
VALUES (1, 1,2),
       (1, 2,2),
       (3, 3,2),
       (2, 2,2),
       (2, 1,2);


INSERT INTO Lugar (nombre, ubicacion, longitud, latitud)
VALUES
    ('Gimnasio Fénix', 'Av. Rivadavia 13500, La Matanza, Buenos Aires', -58.552472, -34.710867),
    ('Club Deportivo Morón', 'Constitución 60, La Matanza, Buenos Aires', -58.624289, -34.677841),
    ('Parque de la Estación', 'Av. Mosconi 1200, La Matanza, Buenos Aires', -58.576573, -34.714259),
    ('Centro González', 'Av. Perón 14000, La Matanza, Buenos Aires', -58.549643, -34.721801),
    ('Plaza San Martín', 'Almafuerte 1900, La Matanza, Buenos Aires', -58.573501, -34.683274),
    ('Gimnasio Olimpia', 'Av. Crovara 7600, La Matanza, Buenos Aires', -58.527000, -34.674600),
    ('Club Atlético La Matanza', 'Av. Juan Manuel de Rosas 9000, La Matanza, Buenos Aires', -58.560780, -34.690830),
    ('Complejo Deportivo Sur', 'Carlos Casares 4000, La Matanza, Buenos Aires', -58.582000, -34.720000),
    ('Parque de la Familia', 'Av. Illia 3000, La Matanza, Buenos Aires', -58.592550, -34.695050),
    ('Centro Deportivo Oeste', 'Av. Brig. Gral. Juan Manuel de Rosas 12000, La Matanza, Buenos Aires', -58.537250, -34.703450),
    ('Polideportivo Municipal', 'Av. Pres. Perón 9900, La Matanza, Buenos Aires', -58.550000, -34.705000),
    ('Gimnasio Power', 'Av. Don Bosco 8500, La Matanza, Buenos Aires', -58.570000, -34.680000),
    ('Espacio Verde', 'Av. San Justo 11000, La Matanza, Buenos Aires', -58.560000, -34.710000),
    ('Club Los Cedros', 'Av. Gral. Paz 7500, La Matanza, Buenos Aires', -58.575000, -34.690000),
    ('Parque Lineal', 'Av. Larrazábal 9000, La Matanza, Buenos Aires', -58.560000, -34.700000),
    ('Gimnasio Fénix II', 'Av. Rivadavia 14000, La Matanza, Buenos Aires', -58.560000, -34.710000),
    ('Centro Deportivo Alvear', 'Av. J. M. Alvear 9300, La Matanza, Buenos Aires', -58.535000, -34.675000),
    ('Complejo Deportivo 3 de Febrero', 'Av. 3 de Febrero 8000, La Matanza, Buenos Aires', -58.565000, -34.695000),
    ('Club Unión', 'Av. Libertador 8800, La Matanza, Buenos Aires', -58.550000, -34.685000),
    ('Plaza El Líbano', 'Av. Carlos Casares 2000, La Matanza, Buenos Aires', -58.582000, -34.707000),
    ('Gimnasio Titanes', 'Av. Independencia 9500, La Matanza, Buenos Aires', -58.540000, -34.680000),
    ('Club Social y Deportivo Mitre', 'Av. Mitre 12000, La Matanza, Buenos Aires', -58.552000, -34.700000),
    ('Centro Deportivo La Matanza Norte', 'Av. San Justo 9500, La Matanza, Buenos Aires', -58.540000, -34.680000),
    ('Polideportivo Municipal II', 'Av. Pres. Perón 8000, La Matanza, Buenos Aires', -58.550000, -34.690000),
    ('Complejo Deportivo El Fortín', 'Av. Juan B. Justo 13000, La Matanza, Buenos Aires', -58.545000, -34.710000),
    ('Club Atlético Huracán', 'Av. Mosconi 15000, La Matanza, Buenos Aires', -58.550000, -34.720000),
    ('Parque Deportivo La Matanza', 'Av. Brig. Gral. Juan Manuel de Rosas 15000, La Matanza, Buenos Aires', -58.555000, -34.730000),
    ('Gimnasio Espartaco', 'Av. Don Bosco 9500, La Matanza, Buenos Aires', -58.575000, -34.685000),
    ('Espacio Recreativo San Justo', 'Av. San Justo 11500, La Matanza, Buenos Aires', -58.560000, -34.720000),
    ('Club Atlético Temperley', 'Av. Alvear 12300, La Matanza, Buenos Aires', -58.552000, -34.730000),
    ('Parque Centenario', 'Av. Patricias Argentinas 900, Caballito, Buenos Aires', -58.438580, -34.607351),
    ('Gimnasio Megatlon', 'Av. Cabildo 4700, Núñez, Buenos Aires', -58.472473, -34.557198),
    ('Club Ciudad de Buenos Aires', 'Av. del Libertador 7500, Núñez, Buenos Aires', -58.456200, -34.544878),
    ('Parque Saavedra', 'Av. García del Río y Av. Crisólogo Larralde, Saavedra, Buenos Aires', -58.487838, -34.558086),
    ('Gimnasio Ocampo Wellness Club', 'Ocampo 2815, Palermo, Buenos Aires', -58.408987, -34.582143),
    ('Club de Amigos', 'Av. Pres. Figueroa Alcorta 3885, Palermo, Buenos Aires', -58.414887, -34.571925),
    ('Parque Las Heras', 'Av. Coronel Díaz y French, Palermo, Buenos Aires', -58.409739, -34.587318),
    ('Gimnasio Sport Club', 'Av. Callao 360, Balvanera, Buenos Aires', -58.398062, -34.610040),
    ('Club Ferro Carril Oeste', 'Av. Avellaneda 1240, Caballito, Buenos Aires', -58.440367, -34.619560),
    ('Parque Chacabuco', 'Av. Asamblea y Emilio Mitre, Caballito, Buenos Aires', -58.438500, -34.628400),
    ('Gimnasio Key Biscayne', 'Mansilla 3181, Palermo, Buenos Aires', -58.413852, -34.597439),
    ('Club Harrods Gath & Chaves', 'Av. Figueroa Alcorta 7110, Núñez, Buenos Aires', -58.453800, -34.552000),
    ('Parque de los Niños', 'Av. Cantilo y Av. Int. Cantilo, Núñez, Buenos Aires', -58.435678, -34.528011),
    ('Gimnasio Megatlon Almagro', 'Av. Rivadavia 4100, Almagro, Buenos Aires', -58.415630, -34.613230),
    ('Club Atlético River Plate', 'Av. Pres. Figueroa Alcorta 7597, Núñez, Buenos Aires', -58.454506, -34.545377),
    ('Parque Indoamericano', 'Av. Castañares y Av. Escalada, Villa Lugano, Buenos Aires', -58.463773, -34.682290),
    ('Gimnasio One Fitness Club', 'Av. Federico Lacroze 1800, Belgrano, Buenos Aires', -58.443951, -34.563710),
    ('Club El Porvenir', 'Ramón Falcón 6100, Mataderos, Buenos Aires', -58.501742, -34.652369),
    ('Parque Avellaneda', 'Av. Directorio 4100, Parque Avellaneda, Buenos Aires', -58.480800, -34.640000),
    ('Gimnasio Boca Gym', 'Necochea 1258, La Boca, Buenos Aires', -58.363655, -34.634661),
    ('Club Atlético Boca Juniors', 'Brandsen 805, La Boca, Buenos Aires', -58.367168, -34.635327),
    ('Parque Lezama', 'Av. Martín García y Av. Paseo Colón, San Telmo, Buenos Aires', -58.367797, -34.620643),
    ('Gimnasio Megatlon Belgrano', 'Av. del Libertador 7200, Belgrano, Buenos Aires', -58.451934, -34.557960),
    ('Club Atlético San Lorenzo de Almagro', 'Av. La Plata 1700, Boedo, Buenos Aires', -58.412108, -34.638684),
    ('Parque Rivadavia', 'Av. Rivadavia 4800, Caballito, Buenos Aires', -58.430000, -34.620400),
('Club Atlético Estudiantil Porteño', 'Av. Cristianía 1234, La Matanza, Buenos Aires', -58.589472, -34.679867),
('Gimnasio Iron Club', 'Av. Tte. Gral. Pablo Riccheri 15200, La Matanza, Buenos Aires', -58.544289, -34.693841),
('Parque de la Juventud', 'Calle Pedro Bonifacio Palacios 1200, La Matanza, Buenos Aires', -58.576000, -34.712259),
('Centro Deportivo 24 de Septiembre', 'Calle Arieta 9000, La Matanza, Buenos Aires', -58.532643, -34.723801),
('Plaza San Justo', 'Calle Irigoyen 1900, La Matanza, Buenos Aires', -58.570000, -34.685274),
('Gimnasio Titan Gym', 'Av. Monseñor Bufano 9500, La Matanza, Buenos Aires', -58.537000, -34.676600),
('Club Social y Deportivo Defensores de La Matanza', 'Calle Belgrano 3200, La Matanza, Buenos Aires', -58.550780, -34.695830),
('Complejo Deportivo Central', 'Calle Arieta 4000, La Matanza, Buenos Aires', -58.582500, -34.721500),
('Parque del Sol', 'Av. Brig. Gral. Juan Manuel de Rosas 10000, La Matanza, Buenos Aires', -58.592550, -34.701050),
('Centro Deportivo Fénix', 'Calle Almafuerte 1200, La Matanza, Buenos Aires', -58.535000, -34.699000),
('Polideportivo Ciudad Evita', 'Av. Crovara 10200, La Matanza, Buenos Aires', -58.548000, -34.705000),
('Gimnasio Strong Power', 'Calle Almafuerte 6500, La Matanza, Buenos Aires', -58.570000, -34.680000),
('Espacio Deportivo Monte Dorrego', 'Calle Don Bosco 1200, La Matanza, Buenos Aires', -58.562000, -34.700000),
('Club La Unión de La Matanza', 'Calle Don Bosco 1100, La Matanza, Buenos Aires', -58.575000, -34.690000),
('Parque Los Robles', 'Calle Larrazábal 2000, La Matanza, Buenos Aires', -58.560000, -34.720000),
('Gimnasio Barracuda', 'Av. Mosconi 12000, La Matanza, Buenos Aires', -58.560000, -34.730000),
('Centro Deportivo El Ciclón', 'Calle Alvear 8900, La Matanza, Buenos Aires', -58.535000, -34.672500),
('Complejo Deportivo Los Andes', 'Calle Carlos Casares 6500, La Matanza, Buenos Aires', -58.550000, -34.725000),
('Club San Jorge', 'Av. Cristianía 7000, La Matanza, Buenos Aires', -58.562000, -34.720000),
('Plaza San José', 'Calle Carlos Casares 900, La Matanza, Buenos Aires', -58.550000, -34.699000),
('Gimnasio La Fortaleza', 'Av. Rivadavia 14600, La Matanza, Buenos Aires', -58.560000, -34.711000),
('Club Deportivo Malvinas Argentinas', 'Calle San Martín 2400, La Matanza, Buenos Aires', -58.552000, -34.700000),
('Centro Deportivo Mariano Moreno', 'Calle San Justo 3200, La Matanza, Buenos Aires', -58.540000, -34.675000),
('Polideportivo 9 de Julio', 'Calle Belgrano 5300, La Matanza, Buenos Aires', -58.550000, -34.690000),
('Complejo Deportivo Buenos Aires', 'Av. Cristianía 9000, La Matanza, Buenos Aires', -58.545000, -34.710000),
('Club Defensores de La Matanza', 'Calle Mosconi 8500, La Matanza, Buenos Aires', -58.550000, -34.720000),
('Parque Deportivo San Justo', 'Calle Alvear 11000, La Matanza, Buenos Aires', -58.555000, -34.730000),
('Gimnasio Strongman', 'Av. Rivadavia 15500, La Matanza, Buenos Aires', -58.575000, -34.685000),
('Espacio Deportivo La Matanza', 'Calle Almafuerte 1100, La Matanza, Buenos Aires', -58.560000, -34.720000),
('Club Deportivo La Matanza', 'Calle Av. Mosconi 9500, La Matanza, Buenos Aires', -58.552000, -34.730000),
('Parque Recreativo Don Bosco', 'Calle Crovara 1500, La Matanza, Buenos Aires', -58.560000, -34.720000),
('Gimnasio Megatlon Caballito', 'Av. Rivadavia 5100, Caballito, Buenos Aires', -58.430440, -34.614200),
('Club All Boys', 'Mercedes 1900, Floresta, Buenos Aires', -58.485800, -34.625400),
('Parque Los Andes', 'Av. Dorrego y Av. Corrientes, Chacarita, Buenos Aires', -58.446500, -34.588800),
('Gimnasio Belgrano Athletic Club', 'Virrey del Pino 3456, Belgrano, Buenos Aires', -58.459400, -34.566200),
('Club Sportivo Barracas', 'Av. Iriarte 2056, Barracas, Buenos Aires', -58.378600, -34.646500),
('Parque Centenario', 'Av. Patricias Argentinas 700, Caballito, Buenos Aires', -58.437800, -34.609500),
('Gimnasio Megatlon Recoleta', 'Av. Las Heras 3000, Recoleta, Buenos Aires', -58.406300, -34.585700),
('Club Ciudad de Buenos Aires', 'Av. del Libertador 7500, Núñez, Buenos Aires', -58.456300, -34.549900),
('Parque Chacabuco', 'Av. Eva Perón y Av. Asamblea, Parque Chacabuco, Buenos Aires', -58.439000, -34.629500),
('Gimnasio Villafañe', 'Av. Montes de Oca 1500, Barracas, Buenos Aires', -58.382200, -34.631900),
('Club Social y Deportivo Platense', 'Zapiola 6275, Saavedra, Buenos Aires', -58.478900, -34.571100),
('Parque de los Patricios', 'Av. Caseros y Monteagudo, Parque de los Patricios, Buenos Aires', -58.405400, -34.637800),
('Gimnasio Balboa Boxing Club', 'Castro Barros 75, Almagro, Buenos Aires', -58.423200, -34.616600),
('Club Gimnasia y Esgrima de Buenos Aires', 'Av. Del Libertador 7500, Núñez, Buenos Aires', -58.454600, -34.546800),
('Parque Indoamericano', 'Av. Castañares y Av. Escalada, Villa Lugano, Buenos Aires', -58.464800, -34.667400),
('Gimnasio Aconcagua', 'Olazabal 5900, Villa Urquiza, Buenos Aires', -58.489600, -34.567800),
('Club Gimnasia y Esgrima de Buenos Aires (Sede Aldao)', 'Dr. Pedro Ignacio Rivera 4300, Villa Urquiza, Buenos Aires', -58.488200, -34.574900),
('Parque Avellaneda', 'Av. Directorio 4100, Parque Avellaneda, Buenos Aires', -58.486800, -34.633200),
('Gimnasio Villalba Fitness Club', 'Av. Cabildo 5000, Belgrano, Buenos Aires', -58.465200, -34.559800),
('Club Sportivo Pereyra', 'Gral. José Gervasio Artigas 840, Parque Chacabuco, Buenos Aires', -58.445600, -34.634200),
('Parque Rivadavia', 'Av. Rivadavia 4900, Caballito, Buenos Aires', -58.427700, -34.618600),
('Gimnasio Juvencia', 'Lope de Vega 600, Vélez Sársfield, Buenos Aires', -58.496400, -34.632900),
('Club Social y Deportivo Franja de Oro', 'Boedo 1400, Boedo, Buenos Aires', -58.416000, -34.624000),
('Parque Lezama', 'Av. Martín García y Av. Paseo Colón, San Telmo, Buenos Aires', -58.366000, -34.620200),
('Gimnasio Olimpo', 'Av. Corrientes 7000, Villa Crespo, Buenos Aires', -58.431200, -34.596800),
('Club Harrods', 'Av. Figueroa Alcorta 7000, Núñez, Buenos Aires', -58.448600, -34.558300),
('Parque Sarmiento', 'Av. Dr. Ricardo Balbín 4750, Saavedra, Buenos Aires', -58.503500, -34.572400),
('Gimnasio San Telmo', 'Defensa 1300, San Telmo, Buenos Aires', -58.377800, -34.617900),
('Club San Miguel', 'Calle Salta 1800, San Cristóbal, Buenos Aires', -58.391200, -34.621100),
('Parque Las Heras', 'Av. Coronel Díaz y French, Palermo, Buenos Aires', -58.409800, -34.587000),
('Gimnasio Comodoro', 'Av. Cabildo 3500, Belgrano, Buenos Aires', -58.450600, -34.573100),
('Club Alvear', 'Marcelo T. de Alvear 1200, Recoleta, Buenos Aires', -58.395700, -34.596600),
('Parque Alberdi', 'Av. Directorio y Av. Emilio Castro, Mataderos, Buenos Aires', -58.514600, -34.645400),
('Gimnasio North Side', 'Av. Congreso 4500, Villa Urquiza, Buenos Aires', -58.474200, -34.568600),
('Club de Amigos', 'Av. Pres. Figueroa Alcorta 3885, Palermo, Buenos Aires', -58.413700, -34.574600),
('Parque Saavedra', 'Av. García del Río y Av. Crisólogo Larralde, Saavedra, Buenos Aires', -58.483700, -34.558800),
('Gimnasio Form Fit', 'Scalabrini Ortiz 2100, Palermo, Buenos Aires', -58.409200, -34.590800),
('Club Atlético Atlanta', 'Humboldt 540, Villa Crespo, Buenos Aires', -58.434500, -34.596100),
('Parque Almirante Brown', 'Av. Castañares y Av. General Paz, Villa Riachuelo, Buenos Aires', -58.472300, -34.682400),
('Gimnasio Terraza', 'Av. de Mayo 1400, Monserrat, Buenos Aires', -58.385300, -34.609700),
('Club 17 de Octubre', 'Av. Chiclana 3000, Boedo, Buenos Aires', -58.417600, -34.631300),
('Parque Manuel Belgrano', 'Av. Figueroa Alcorta 7000, Belgrano, Buenos Aires', -58.457800, -34.553600),
('Gimnasio Su Gym', 'Av. Juan B. Justo 3000, Palermo, Buenos Aires', -58.439900, -34.595100),
('Club Estudiantes de Buenos Aires', 'Av. Francisco Beiró 2800, Villa Devoto, Buenos Aires', -58.506200, -34.607600),
('Parque De Los Niños', 'Gral. Paz y Av. Cantilo, Núñez, Buenos Aires', -58.475800, -34.532000),
('Gimnasio Esparta', 'Av. San Juan 3300, San Cristóbal, Buenos Aires', -58.403400, -34.620500),
('Club Liniers', 'Carhué 1800, Liniers, Buenos Aires', -58.535000, -34.656100),
('Parque Los Andes', 'Av. Dorrego y Av. Corrientes, Chacarita, Buenos Aires', -58.445500, -34.590600),
('Gimnasio Megatlon Caballito II', 'Av. Rivadavia 5200, Caballito, Buenos Aires', -58.431800, -34.614600),
('Club Social y Deportivo Mitre', 'Pueyrredón 1500, Villa Mitre, Buenos Aires', -58.451000, -34.606500),
('Parque Patricios', 'Av. Caseros y La Rioja, Parque Patricios, Buenos Aires', -58.398500, -34.631800),
('Gimnasio Mega Sport', 'Av. Corrientes 2400, Balvanera, Buenos Aires', -58.407300, -34.607000),
('Club Social y Deportivo Comunicaciones', 'Av. San Martín 5125, Agronomía, Buenos Aires', -58.482700, -34.598000),
('Parque Rivadavia', 'Av. Rivadavia 4900, Caballito, Buenos Aires', -58.428300, -34.616900),
('Gimnasio Crossfit Devoto', 'Av. Salvador María del Carril 4500, Villa Devoto, Buenos Aires', -58.515400, -34.603800),
('Club Social y Deportivo San Jorge', 'Av. Álvarez Thomas 2700, Villa Urquiza, Buenos Aires', -58.487300, -34.574200),
('Parque Alberdi', 'Av. Directorio 4200, Mataderos, Buenos Aires', -58.514800, -34.646200),
('Gimnasio Rosario Central', 'Juan B. Justo 9000, Villa Real, Buenos Aires', -58.520000, -34.611400),
('Club Social y Deportivo Mitre', 'Av. Congreso 5500, Villa Urquiza, Buenos Aires', -58.484300, -34.566800),
('Parque Chacabuco', 'Av. Eva Perón y Av. Asamblea, Parque Chacabuco, Buenos Aires', -58.439300, -34.629800),
('Gimnasio Le Park', 'Av. del Libertador 5000, Palermo, Buenos Aires', -58.422200, -34.582100),
('Club Allende', 'Andrés Lamas 2400, Villa del Parque, Buenos Aires', -58.482500, -34.612000),
('Parque Saavedra', 'Av. García del Río y Av. Balbín, Saavedra, Buenos Aires', -58.484700, -34.561300),
('Gimnasio Aerofit', 'Av. Gaona 4600, Flores, Buenos Aires', -58.475800, -34.616400),
('Club Gimnasia y Esgrima de Buenos Aires (Sede Figueroa Alcorta)', 'Av. Figueroa Alcorta 5500, Núñez, Buenos Aires', -58.451100, -34.560100),
('Parque Los Andes', 'Av. Corrientes y Av. Dorrego, Chacarita, Buenos Aires', -58.446100, -34.589200),
('Gimnasio Energy Gym', 'Av. Rivadavia 6300, Flores, Buenos Aires', -58.470200, -34.620800),
('Club GEVP', 'Av. Nazca 4700, Villa del Parque, Buenos Aires', -58.497400, -34.606200),
('Parque General Paz', 'Av. Gral. Paz y Av. Rivadavia, Liniers, Buenos Aires', -58.530800, -34.640200),
('Gimnasio Megatlon Almagro', 'Av. Díaz Vélez 3900, Almagro, Buenos Aires', -58.415000, -34.613500);

INSERT INTO Lugar_TipoEntrenamiento (lugar_id, tipoEntrenamiento_id)
VALUES
    (1, 1), -- Gimnasio Fénix -> Musculacion
    (2, 1), -- Club Deportivo Morón -> Musculacion
    (3, 3), -- Parque de la Estación -> Cardio
    (3, 2), -- Parque de la Estación -> Calistenia
    (5, 3), -- Plaza San Martín -> Cardio
    (5, 2), -- Plaza San Martín -> Calistenia
    (6, 1), -- Gimnasio Olimpia -> Musculacion
    (6, 2), -- Gimnasio Olimpia -> Calistenia
    (7, 1), -- Club Atlético La Matanza -> Musculacion
    (8, 3), -- Complejo Deportivo Sur -> Cardio
    (8, 2), -- Complejo Deportivo Sur -> Calistenia
    (9, 3), -- Parque de la Familia -> Cardio
    (10, 1), -- Centro Deportivo Oeste -> Musculacion
    (11, 3), -- Polideportivo Municipal -> Cardio
    (12, 1), -- Gimnasio Power -> Musculacion
    (12, 2), -- Gimnasio Power -> Calistenia
    (13, 3), -- Espacio Verde -> Cardio
    (14, 1), -- Club Los Cedros -> Musculacion
    (15, 3), -- Parque Lineal -> Cardio
    (16, 1), -- Gimnasio Fénix II -> Musculacion
    (16, 2), -- Gimnasio Fénix II -> Calistenia
    (17, 3), -- Centro Deportivo Alvear -> Cardio
    (18, 1), -- Complejo Deportivo 3 de Febrero -> Musculacion
    (19, 2), -- Club Unión -> Calistenia
    (20, 3), -- Plaza El Líbano -> Cardio
    (21, 1), -- Gimnasio Titanes -> Musculacion
    (21, 3), -- Gimnasio Titanes -> Cardio
    (22, 2), -- Club Social y Deportivo Mitre -> Calistenia
    (23, 3), -- Centro Deportivo La Matanza Norte -> Cardio
    (23, 1), -- Centro Deportivo La Matanza Norte -> Musculacion
    (24, 2), -- Polideportivo Municipal II -> Calistenia
    (24, 3), -- Polideportivo Municipal II -> Cardio
    (25, 1), -- Complejo Deportivo El Fortín -> Musculacionn
    (26, 3), -- Club Atlético Huracán -> Cardio
    (26, 2), -- Club Atlético Huracán -> Calistenia
    (27, 1), -- Parque Deportivo La Matanza -> Musculacion
    (28, 2), -- Gimnasio Espartaco -> Calistenia
    (29, 3), -- Espacio Recreativo San Justo -> Cardio
    (30, 1), -- Club Atlético Temperley -> Musculacion
    (31, 2), -- Parque Centenario -> Cardio
    (32, 1), -- Gimnasio Megatlon -> Musculacion
    (32, 2), -- Gimnasio Megatlon -> Cardio
    (33, 1), -- Club Ciudad de Buenos Aires -> Musculacion
    (33, 3), -- Club Ciudad de Buenos Aires -> Calistenia
    (34, 2), -- Parque Saavedra -> Cardio
    (35, 1), -- Gimnasio Ocampo Wellness Club -> Musculacion
    (35, 2), -- Gimnasio Ocampo Wellness Club -> Cardio
    (36, 3), -- Club de Amigos -> Calistenia
    (37, 2), -- Parque Las Heras -> Cardio
    (38, 1), -- Gimnasio Sport Club -> Musculacion
    (39, 1), -- Club Ferro Carril Oeste -> Musculacion
    (39, 3), -- Club Ferro Carril Oeste -> Calistenia
    (40, 2), -- Parque Chacabuco -> Cardio
    (41, 1), -- Gimnasio Key Biscayne -> Musculacion
    (42, 3), -- Club Harrods Gath & Chaves -> Calistenia
    (43, 2), -- Parque de los Niños -> Cardio
    (44, 1), -- Gimnasio Megatlon Almagro -> Musculacion
    (44, 2), -- Gimnasio Megatlon Almagro -> Cardio
    (45, 1), -- Club Atlético River Plate -> Musculacion
    (45, 2), -- Club Atlético River Plate -> Cardio
    (46, 3), -- Parque Indoamericano -> Calistenia
    (47, 1), -- Gimnasio One Fitness Club -> Musculacion
    (47, 2), -- Gimnasio One Fitness Club -> Cardio
    (48, 1), -- Club El Porvenir -> Musculacion
    (48, 3), -- Club El Porvenir -> Calistenia
    (49, 2), -- Parque Avellaneda -> Cardio
    (50, 1), -- Gimnasio Boca Gym -> Musculacion
    (51, 2), -- Club Atlético Boca Juniors -> Cardio
    (51, 3), -- Club Atlético Boca Juniors -> Calistenia
    (52, 2), -- Parque Lezama -> Cardio
    (53, 1), -- Gimnasio Megatlon Belgrano -> Musculacion
    (54, 1), -- Club Atlético San Lorenzo de Almagro -> Musculacion
    (54, 2), -- Club Atlético San Lorenzo de Almagro -> Cardio
    (55, 2), -- Parque Rivadavia -> Cardio
    (56, 1), -- Club Atlético Estudiantil Porteño -> Musculacion
    (56, 3), -- Club Atlético Estudiantil Porteño -> Cardio
    (57, 1), -- Gimnasio Iron Club -> Musculacion
    (57, 2), -- Gimnasio Iron Club -> Calistenia
    (58, 2), -- Parque de la Juventud -> Calistenia
    (58, 3), -- Parque de la Juventud -> Cardio
    (59, 3), -- Centro Deportivo 24 de Septiembre -> Cardio
    (60, 2), -- Plaza San Justo -> Calistenia
    (61, 1), -- Gimnasio Titan Gym -> Musculacion
    (62, 3), -- Club Social y Deportivo Defensores de La Matanza -> Cardio
    (63, 2), -- Complejo Deportivo Central -> Calistenia
    (63, 3), -- Complejo Deportivo Central -> Cardio
    (64, 2), -- Parque del Sol -> Calistenia
    (65, 3), -- Centro Deportivo Fénix -> Cardio
    (66, 2), -- Polideportivo Ciudad Evita -> Calistenia
    (67, 1), -- Gimnasio Strong Power -> Musculacion
    (67, 3), -- Gimnasio Strong Power -> Cardio
    (68, 2), -- Espacio Deportivo Monte Dorrego -> Calistenia
    (68, 3), -- Espacio Deportivo Monte Dorrego -> Cardio
    (69, 1), -- Club La Unión de La Matanza -> Musculacion
    (69, 3), -- Club La Unión de La Matanza -> Cardio
    (70, 3), -- Parque Los Robles -> Cardio
    (71, 1), -- Gimnasio Barracuda -> Musculacion
    (71, 2), -- Gimnasio Barracuda -> Calistenia
    (72, 3), -- Centro Deportivo El Ciclón -> Cardio
    (72, 1), -- Centro Deportivo El Ciclón -> Musculacion
    (73, 2), -- Complejo Deportivo Los Andes -> Calistenia
    (73, 3), -- Complejo Deportivo Los Andes -> Cardio
    (74, 1), -- Club San Jorge -> Musculacion
    (75, 2), -- Plaza San José -> Calistenia
    (75, 3), -- Plaza San José -> Cardio
    (76, 1), -- Gimnasio La Fortaleza -> Musculacion
    (77, 2), -- Club Deportivo Malvinas Argentinas -> Calistenia
    (78, 3), -- Centro Deportivo Mariano Moreno -> Cardio
    (78, 1), -- Centro Deportivo Mariano Moreno -> Musculacion
    (79, 2), -- Polideportivo 9 de Julio -> Calistenia
    (79, 3), -- Polideportivo 9 de Julio -> Cardio
    (80, 1), -- Complejo Deportivo Buenos Aires -> Musculacion
    (80, 2), -- Complejo Deportivo Buenos Aires -> Calistenia
    (81, 1), -- Club Defensores de La Matanza -> Musculacion
    (81, 2), -- Club Defensores de La Matanza -> Calistenia
    (82, 3), -- Parque Deportivo San Justo -> Cardio
    (83, 1), -- Gimnasio Strongman -> Musculacion
    (83, 2), -- Gimnasio Strongman -> Calistenia
    (84, 2), -- Espacio Deportivo La Matanza -> Calistenia
    (84, 3), -- Espacio Deportivo La Matanza -> Cardio
    (85, 1), -- Club Deportivo La Matanza -> Musculacion
    (85, 2), -- Club Deportivo La Matanza -> Calistenia
    (86, 3), -- Parque Recreativo Don Bosco -> Cardio
    (86, 2), -- Parque Recreativo Don Bosco -> Calistenia
    (87, 1),  -- Gimnasio Megatlon Caballito -> Musculacion
                                                                             (87, 3),  -- Gimnasio Megatlon Caballito -> Cardio
                                                                             (88, 1),  -- Club All Boys -> Musculacion
                                                                             (88, 2),  -- Club All Boys -> Calistenia
                                                                             (89, 3),  -- Parque Los Andes -> Cardio
                                                                             (90, 1),  -- Gimnasio Belgrano Athletic Club -> Musculacion
                                                                             (90, 3),  -- Gimnasio Belgrano Athletic Club -> Cardio
                                                                             (91, 1),  -- Club Sportivo Barracas -> Musculacion
                                                                             (91, 2),  -- Club Sportivo Barracas -> Calistenia
                                                                             (92, 3),  -- Parque Centenario -> Cardio
                                                                             (93, 1),  -- Gimnasio Megatlon Recoleta -> Musculacion
                                                                             (93, 2),  -- Gimnasio Megatlon Recoleta -> Calistenia
                                                                             (94, 1),  -- Club Ciudad de Buenos Aires -> Musculacion
                                                                             (94, 3),  -- Club Ciudad de Buenos Aires -> Cardio
                                                                             (95, 2),  -- Parque Chacabuco -> Calistenia
                                                                             (96, 1),  -- Gimnasio Villafañe -> Musculacion
                                                                             (96, 3),  -- Gimnasio Villafañe -> Cardio
                                                                             (97, 1),  -- Club Social y Deportivo Platense -> Musculacion
                                                                             (97, 2),  -- Club Social y Deportivo Platense -> Calistenia
                                                                             (98, 1),  -- Parque de los Patricios -> Musculacion
                                                                             (99, 1),  -- Gimnasio Balboa Boxing Club -> Musculacion
                                                                             (99, 2),  -- Gimnasio Balboa Boxing Club -> Calistenia
                                                                             (100, 1),  -- Club Gimnasia y Esgrima de Buenos Aires -> Musculacion
                                                                             (100, 3),  -- Club Gimnasia y Esgrima de Buenos Aires -> Cardio
                                                                             (101, 2),  -- Parque Indoamericano -> Calistenia
                                                                             (102, 1),  -- Gimnasio Aconcagua -> Musculacion
                                                                             (102, 2),  -- Gimnasio Aconcagua -> Calistenia
                                                                             (103, 1),  -- Club Gimnasia y Esgrima de Buenos Aires (Sede Aldao) -> Musculacion
                                                                             (103, 3),  -- Club Gimnasia y Esgrima de Buenos Aires (Sede Aldao) -> Cardio
                                                                             (104, 2),  -- Parque Avellaneda -> Calistenia
                                                                             (105, 1),  -- Gimnasio Villalba Fitness Club -> Musculacion
                                                                             (105, 2),  -- Gimnasio Villalba Fitness Club -> Calistenia
                                                                             (106, 1),  -- Club Sportivo Pereyra -> Musculacion
                                                                             (106, 3),  -- Club Sportivo Pereyra -> Cardio
                                                                             (107, 2),  -- Parque Rivadavia -> Calistenia
                                                                             (108, 1),  -- Gimnasio Juvencia -> Musculacion
                                                                             (108, 3),  -- Gimnasio Juvencia -> Cardio
                                                                             (109, 1),  -- Club Social y Deportivo Franja de Oro -> Musculacion
                                                                             (109, 3),  -- Club Social y Deportivo Franja de Oro -> Cardio
                                                                             (110, 2),  -- Parque Lezama -> Calistenia
                                                                             (111, 1),  -- Gimnasio Olimpo -> Musculacion
                                                                             (111, 3),  -- Gimnasio Olimpo -> Cardio
                                                                             (112, 1),  -- Club Harrods -> Musculacion
                                                                             (112, 2),  -- Club Harrods -> Calistenia
                                                                             (113, 3),  -- Parque Sarmiento -> Cardio
                                                                             (114, 1),  -- Gimnasio San Telmo -> Musculacion
                                                                             (114, 2),  -- Gimnasio San Telmo -> Calistenia
                                                                             (115, 1),  -- Club San Miguel -> Musculacion
                                                                             (115, 3),  -- Club San Miguel -> Cardio
                                                                             (116, 2),  -- Parque Las Heras -> Calistenia
                                                                             (117, 1),  -- Gimnasio Comodoro -> Musculacion
                                                                             (117, 3),  -- Gimnasio Comodoro -> Cardio
                                                                             (118, 1),  -- Club Alvear -> Musculacion
                                                                             (118, 3),  -- Club Alvear -> Cardio
                                                                             (119, 2),  -- Parque Alberdi -> Calistenia
                                                                             (120, 1),  -- Gimnasio North Side -> Musculacion
                                                                             (120, 2),  -- Gimnasio North Side -> Calistenia
                                                                             (121, 1),  -- Club de Amigos -> Musculacion
                                                                             (121, 3),  -- Club de Amigos -> Cardio
                                                                             (122, 2),  -- Parque Saavedra -> Calistenia
                                                                             (123, 1),  -- Gimnasio Form Fit -> Musculacion
                                                                             (123, 3),  -- Gimnasio Form Fit -> Cardio
                                                                             (124, 1),  -- Club Atlético Atlanta -> Musculacion
                                                                             (124, 2),  -- Club Atlético Atlanta -> Calistenia
                                                                             (125, 3),  -- Parque Almirante Brown -> Cardio
                                                                             (126, 1),  -- Gimnasio Terraza -> Musculacion
                                                                             (126, 3),  -- Gimnasio Terraza -> Cardio
                                                                             (127, 1),  -- Club 17 de Octubre -> Musculacion
                                                                             (127, 2),  -- Club 17 de Octubre -> Calistenia
                                                                             (128, 1),  -- Parque Manuel Belgrano -> Musculacion
                                                                             (128, 3),  -- Parque Manuel Belgrano -> Cardio
                                                                             (129, 2),  -- Gimnasio Su Gym -> Calistenia
                                                                             (130, 1),  -- Club Estudiantes de Buenos Aires -> Musculacion
                                                                             (130, 3),  -- Club Estudiantes de Buenos Aires -> Cardio
                                                                             (131, 2),  -- Parque De Los Niños -> Calistenia
                                                                             (132, 1),  -- Gimnasio Esparta -> Musculacion
                                                                             (132, 3),  -- Gimnasio Esparta -> Cardio
                                                                             (133, 1),  -- Club Liniers -> Musculacion
                                                                             (133, 2),  -- Club Liniers -> Calistenia
                                                                             (134, 3),  -- Parque Los Andes -> Cardio
                                                                             (135, 1),  -- Gimnasio Megatlon Caballito II -> Musculacion
                                                                             (135, 2),  -- Gimnasio Megatlon Caballito II -> Calistenia
                                                                             (136, 1),  -- Club Social y Deportivo Mitre -> Musculacion
                                                                             (136, 3),  -- Club Social y Deportivo Mitre -> Cardio
                                                                             (137, 2),  -- Parque Patricios -> Calistenia
                                                                             (138, 1),  -- Gimnasio Mega Sport -> Musculacion
                                                                             (138, 3),  -- Gimnasio Mega Sport -> Cardio
                                                                             (139, 1),  -- Club Social y Deportivo Comunicaciones -> Musculacion
                                                                             (139, 2),  -- Club Social y Deportivo Comunicaciones -> Calistenia
                                                                             (140, 3),  -- Parque Rivadavia -> Cardio
                                                                             (141, 1),  -- Gimnasio Los Andes -> Musculacion
                                                                             (141, 2),  -- Gimnasio Los Andes -> Calistenia
                                                                             (142, 1),  -- Club San Lorenzo -> Musculacion
                                                                             (142, 3);  -- Club San Lorenzo -> Cardio;


-- Insertar datos de prueba en RutinaSemanal
-- INSERT INTO RutinaSemanal (usuario_id) VALUES
-- (1),
-- (2),
-- (3),
-- (4),
-- (5);

-- Insertar datos de prueba en RutinaDiaria
/*INSERT INTO RutinaDiaria (diaSemana, descripcion, rutina_semanal_id) VALUES
('Lunes', 'Rutina de Lunes', 1),
('Martes', 'Rutina de Martes', 1),
('Miércoles', 'Rutina de Miércoles', 1),
('Jueves', 'Rutina de Jueves', 2),
('Viernes', 'Rutina de Viernes', 2),
('Sábado', 'Rutina de Sábado', 3),
('Domingo', 'Rutina de Domingo', 3),
('Lunes', 'Rutina de Lunes', 4),
('Martes', 'Rutina de Martes', 4),
('Miércoles', 'Rutina de Miércoles', 5);
*/
-- Insertar datos de prueba en Ejercicio

-- Insertar ejercicios
INSERT INTO ejercicio (descripcion, duracion, nombre, realizado, tipo, primario)
VALUES ('Press de Banca. Realiza 3 series de 12 repeticiones.', 15, 'Press de Banca', 0, 'Musculacion', FALSE),
       ('Sentadillas. Realiza 4 series de 10 repeticiones.', 20, 'Sentadillas', 0, 'Musculacion', FALSE),
       ('Peso Muerto. Realiza 3 series de 8 repeticiones.', 25, 'Peso Muerto', 0, 'Musculacion', FALSE),
       ('Curl de Bíceps. Realiza 3 series de 10 repeticiones.', 15, 'Curl de Bíceps', 0, 'Musculacion', FALSE),
       ('Press de Hombros. Realiza 3 series de 12 repeticiones.', 20, 'Press de Hombros', 0, 'Musculacion', FALSE),
       ('Remo con Barra. Realiza 3 series de 12 repeticiones.', 20, 'Remo con Barra', 0, 'Musculacion', FALSE),
       ('Dominadas. Realiza 3 series de 8 repeticiones.', 20, 'Dominadas', 0, 'Musculacion', FALSE),
       ('Fondos en paralelas. Realiza 3 series de 10 repeticiones.', 15, 'Fondos en Paralelas', 0, 'Musculacion',
        FALSE),
       ('Prensa de Piernas. Realiza 3 series de 12 repeticiones.', 20, 'Prensa de Piernas', 0, 'Musculacion', FALSE),
       ('Extensiones de Tríceps en Polea. Realiza 3 series de 12 repeticiones.', 15, 'Extensiones de Tríceps en Polea',
        0, 'Musculacion', FALSE),
       ('Elevaciones laterales. Realiza 3 series de 12 repeticiones.', 15, 'Elevaciones laterales', 0, 'Musculacion',
        FALSE),
       ('Curl de Femoral. Realiza 3 series de 10 repeticiones.', 20, 'Curl de Femoral', 0, 'Musculacion', FALSE),
       ('Elevaciones de Gemelos. Realiza 3 series de 15 repeticiones.', 20, 'Elevaciones de Gemelos', 0, 'Musculacion',
        FALSE),
       ('Pull Over. Realiza 3 series de 12 repeticiones.', 20, 'Pull Over', 0, 'Musculacion', FALSE),
       ('Pájaros. Realiza 3 series de 12 repeticiones.', 15, 'Pájaros', 0, 'Musculacion', FALSE),
       ('Press de Banca Inclinado. Realiza 3 series de 10 repeticiones.', 20, 'Press de Banca Inclinado', 0,
        'Musculacion', FALSE),
       ('Press Francés. Realiza 3 series de 12 repeticiones.', 15, 'Press Francés', 0, 'Musculacion', FALSE),
       ('Pullover con mancuerna. Realiza 3 series de 12 repeticiones.', 20, 'Pullover con mancuerna', 0, 'Musculacion',
        FALSE),
       ('Aperturas con mancuernas. Realiza 3 series de 12 repeticiones.', 15, 'Aperturas con mancuernas', 0,
        'Musculacion', FALSE),
       ('Press militar. Realiza 3 series de 12 repeticiones.', 20, 'Press militar', 0, 'Musculacion', FALSE),
       ('Flexiones de Brazos', 15, 'Flexiones de Brazos', 0, 'Calistenia', FALSE),
       ('Burpees', 20, 'Burpees', 0, 'Calistenia', FALSE),
       ('Plancha', 20, 'Plancha', 0, 'Calistenia', TRUE),
       ('Dominadas en Barra', 20, 'Dominadas en Barra', 0, 'Calistenia', TRUE),
       ('Flexiones Diamante', 15, 'Flexiones Diamante', 0, 'Calistenia', TRUE),
       ('Flexiones con Palmada', 15, 'Flexiones con Palmada', 0, 'Calistenia', FALSE),
       ('Sentadillas', 20, 'Sentadillas', 0, 'Calistenia', TRUE),
       ('Plancha Lateral', 20, 'Plancha Lateral', 0, 'Calistenia', TRUE),
       ('Flexiones en Pica', 15, 'Flexiones en Pica', 0, 'Calistenia', FALSE),
       ('Abdominales', 15, 'Abdominales', 0, 'Calistenia', TRUE),
       ('Mountain Climbers', 15, 'Mountain Climbers', 0, 'Calistenia', FALSE),
       ('Fondos en Dips', 15, 'Fondos en Dips', 0, 'Calistenia', TRUE),
       ('Flexiones con apoyo', 15, 'Flexiones con apoyo', 0, 'Calistenia', FALSE),
       ('Sentadillas con salto', 20, 'Sentadillas con salto', 0, 'Calistenia', TRUE),
       ('Escaladores', 15, 'Escaladores', 0, 'Calistenia', FALSE),
       ('Plancha con Elevación de Piernas', 20, 'Plancha con Elevación de Piernas', 0, 'Calistenia', TRUE),
       ('Hollow Body Hold', 20, 'Hollow Body Hold', 0, 'Calistenia', TRUE),
       ('Superman', 15, 'Superman', 0, 'Calistenia', TRUE),
       ('Tijeras', 15, 'Tijeras', 0, 'Calistenia', TRUE),
       ('Carrera en el Sitio', 10, 'Carrera en el Sitio', 0, 'Cardio', FALSE),
       ('Jumping Jacks', 15, 'Jumping Jacks', 0, 'Cardio', FALSE),
       ('Burpees', 20, 'Burpees', 0, 'Cardio', TRUE),
       ('Skipping', 10, 'Skipping', 0, 'Cardio', FALSE),
       ('Mountain Climbers', 15, 'Mountain Climbers', 0, 'Cardio', TRUE),
       ('Saltos de Tijera', 15, 'Saltos de Tijera', 0, 'Cardio', FALSE),
       ('Carrera en el Lugar', 10, 'Carrera en el Lugar', 0, 'Cardio', FALSE),
       ('Plancha con Elevación de Piernas', 10, 'Plancha con Elevación de Piernas', 0, 'Cardio', FALSE),
       ('Elevación de Rodillas', 10, 'Elevación de Rodillas', 0, 'Cardio', FALSE),
       ('Skipping con Cuerda', 15, 'Skipping con Cuerda', 0, 'Cardio', FALSE),
       ('Sentadillas con Salto', 10, 'Sentadillas con Salto', 0, 'Cardio', FALSE),
       ('Jump Squats', 10, 'Jump Squats', 0, 'Cardio', TRUE),
       ('Carrera hacia Atrás', 10, 'Carrera hacia Atrás', 0, 'Cardio', FALSE),
       ('Plancha con Palmada', 15, 'Plancha con Palmada', 0, 'Cardio', FALSE),
       ('Carrera con Rodillas Altas', 15, 'Carrera con Rodillas Altas', 0, 'Cardio', FALSE),
       ('Sprints de Corta Distancia', 10, 'Sprints de Corta Distancia', 0, 'Cardio', FALSE),
       ('Escaladores', 15, 'Escaladores', 0, 'Cardio', TRUE),
       ('Flexiones con Salto', 15, 'Flexiones con Salto', 0, 'Cardio', FALSE),
       ('Plancha con Rotación', 15, 'Plancha con Rotación', 0, 'Cardio', FALSE),
       ('Sprints en Pendiente', 15, 'Sprints en Pendiente', 0, 'Cardio', FALSE),
       ('Sentadillas Búlgaras. Realiza 3 series de 12 repeticiones por pierna.', 20, 'Sentadillas Búlgaras', 0,
        'Musculacion', FALSE),
       ('Press Arnold. Realiza 3 series de 10 repeticiones.', 20, 'Press Arnold', 0, 'Musculacion', FALSE),
       ('Fondos en Máquina. Realiza 3 series de 12 repeticiones.', 15, 'Fondos en Máquina', 0, 'Musculacion', FALSE),
       ('Curl de Bíceps con Barra Z. Realiza 3 series de 10 repeticiones.', 15, 'Curl de Bíceps con Barra Z', 0,
        'Musculacion', FALSE),
       ('Peso Muerto Rumano. Realiza 3 series de 8 repeticiones.', 25, 'Peso Muerto Rumano', 0, 'Musculacion', FALSE),
       ('Patada de Tríceps en Máquina. Realiza 3 series de 12 repeticiones.', 15, 'Patada de Tríceps en Máquina', 0,
        'Musculacion', FALSE),
       ('Elevaciones laterales en Polea. Realiza 3 series de 12 repeticiones.', 15, 'Elevaciones laterales en Polea', 0,
        'Musculacion', FALSE),
       ('Curl de Bíceps con Mancuerna. Realiza 3 series de 10 repeticiones por brazo.', 15,
        'Curl de Bíceps con Mancuerna', 0, 'Musculacion', FALSE),
       ('Sentadillas Frontales. Realiza 3 series de 12 repeticiones.', 20, 'Sentadillas Frontales', 0, 'Musculacion',
        FALSE),
       ('Pulldown al Pecho. Realiza 3 series de 12 repeticiones.', 20, 'Pulldown al Pecho', 0, 'Musculacion', FALSE),
       ('Flexiones en Máquina. Realiza 3 series de 12 repeticiones.', 15, 'Flexiones en Máquina', 0, 'Musculacion',
        FALSE),
       ('Curl de Bíceps Martillo. Realiza 3 series de 10 repeticiones.', 15, 'Curl de Bíceps Martillo', 0,
        'Musculacion', FALSE),
       ('Prensa de Hombros con Mancuernas. Realiza 3 series de 12 repeticiones.', 20,
        'Prensa de Hombros con Mancuernas', 0, 'Musculacion', FALSE),
       ('Curl de Bíceps con Barra EZ. Realiza 3 series de 10 repeticiones.', 15, 'Curl de Bíceps con Barra EZ', 0,
        'Musculacion', FALSE),
       ('Pull Over con Polea Alta. Realiza 3 series de 12 repeticiones.', 20, 'Pull Over con Polea Alta', 0,
        'Musculacion', FALSE),
       ('Curl de Bíceps en Polea Baja. Realiza 3 series de 12 repeticiones.', 15, 'Curl de Bíceps en Polea Baja', 0,
        'Musculacion', FALSE),
       ('Pulldown Trasero en Polea. Realiza 3 series de 12 repeticiones.', 20, 'Pulldown Trasero en Polea', 0,
        'Musculacion', FALSE),
       ('Press de Hombros con Barra. Realiza 3 series de 12 repeticiones.', 20, 'Press de Hombros con Barra', 0,
        'Musculacion', FALSE),
       ('Fondos en Máquina con agarre cerrado. Realiza 3 series de 12 repeticiones.', 15,
        'Fondos en Máquina con agarre cerrado', 0, 'Musculacion', FALSE),
       ('Curl de Bíceps en Banco Scott. Realiza 3 series de 10 repeticiones.', 15, 'Curl de Bíceps en Banco Scott', 0,
        'Musculacion', FALSE),
       ('Elevaciones Laterales con Mancuernas. Realiza 3 series de 12 repeticiones.', 15,
        'Elevaciones Laterales con Mancuernas', 0, 'Musculacion', FALSE),
       ('Pulldown al Pecho con agarre cerrado. Realiza 3 series de 12 repeticiones.', 20,
        'Pulldown al Pecho con agarre cerrado', 0, 'Musculacion', FALSE),
       ('Extensiones de Tríceps con Mancuerna. Realiza 3 series de 12 repeticiones.', 15,
        'Extensiones de Tríceps con Mancuerna', 0, 'Musculacion', FALSE),
       ('Curl de Bíceps con Cable. Realiza 3 series de 10 repeticiones.', 15, 'Curl de Bíceps con Cable', 0,
        'Musculacion', FALSE),
       ('Prensa de Hombros con Barra por detrás de la cabeza. Realiza 3 series de 12 repeticiones.', 20,
        'Prensa de Hombros con Barra por detrás de la cabeza', 0, 'Musculacion', TRUE),
       ('Prensa de Hombros con Mancuernas Sentado. Realiza 3 series de 12 repeticiones.', 20,
        'Prensa de Hombros con Mancuernas Sentado', 0, 'Musculacion', TRUE),
       ('Press Francés con Mancuerna. Realiza 3 series de 12 repeticiones.', 15, 'Press Francés con Mancuerna', 0,
        'Musculacion', TRUE),
       ('Press de Banca Declinado. Realiza 3 series de 10 repeticiones.', 20, 'Press de Banca Declinado', 0,
        'Musculacion', TRUE),
       ('Press de Banca con Mancuernas. Realiza 3 series de 12 repeticiones.', 20, 'Press de Banca con Mancuernas', 0,
        'Musculacion', TRUE),
       ('Press Militar con Barra. Realiza 3 series de 12 repeticiones.', 20, 'Press Militar con Barra', 0,
        'Musculacion', TRUE),
       ('Press Francés con Barra. Realiza 3 series de 12 repeticiones.', 15, 'Press Francés con Barra', 0,
        'Musculacion', TRUE),
       ('Elevaciones Frontales con Mancuernas. Realiza 3 series de 12 repeticiones.', 15,
        'Elevaciones Frontales con Mancuernas', 0, 'Musculacion', TRUE),
       ('Curl de Bíceps en Polea Alta. Realiza 3 series de 12 repeticiones.', 15, 'Curl de Bíceps en Polea Alta', 0,
        'Musculacion', TRUE),
       ('Curl de Bíceps Martillo con Mancuernas. Realiza 3 series de 10 repeticiones.', 15,
        'Curl de Bíceps Martillo con Mancuernas', 0, 'Musculacion', TRUE),
       ('Fondos en Máquina con agarre abierto. Realiza 3 series de 12 repeticiones.', 15,
        'Fondos en Máquina con agarre abierto', 0, 'Musculacion', TRUE),
       ('Peso Muerto Convencional. Realiza 3 series de 8 repeticiones.', 25, 'Peso Muerto Convencional', 0,
        'Musculacion', TRUE),
       ('Dominadas con agarre ancho. Realiza 3 series de 8 repeticiones.', 20, 'Dominadas con agarre ancho', 0,
        'Musculacion', TRUE),
       ('Remo con Mancuerna. Realiza 3 series de 12 repeticiones.', 20, 'Remo con Mancuerna', 0, 'Musculacion', TRUE),
       ('Elevaciones Laterales con Cable. Realiza 3 series de 12 repeticiones.', 15, 'Elevaciones Laterales con Cable',
        0, 'Musculacion', TRUE),
       ('Press de Banca Inclinado con Barra. Realiza 3 series de 10 repeticiones.', 20,
        'Press de Banca Inclinado con Barra', 0, 'Musculacion', TRUE),
       ('Curl de Bíceps 21. Realiza 3 series de 21 repeticiones.', 15, 'Curl de Bíceps 21', 0, 'Musculacion', TRUE),
       ('Fondos en Máquina para Tríceps. Realiza 3 series de 12 repeticiones.', 15, 'Fondos en Máquina para Tríceps', 0,
        'Musculacion', TRUE),
       ('Peso Muerto Sumo. Realiza 3 series de 8 repeticiones.', 25, 'Peso Muerto Sumo', 0, 'Musculacion', TRUE),
       ('Pull Over con Mancuerna. Realiza 3 series de 12 repeticiones.', 20, 'Pull Over con Mancuerna', 0,
        'Musculacion', TRUE);
-- Insertar datos de prueba en rutina_diaria_ejercicio
-- INSERT INTO rutina_diaria_ejercicio (rutina_diaria_id, ejercicio_id) VALUES
-- (1, 1),
-- (1, 2),
-- (1, 3),
-- (2, 4),
-- (2, 5),
-- (3, 6),
-- (3, 7),
-- (4, 8),
-- (4, 9),
-- (5, 10);

-- Insertar datos de prueba en membresia
-- INSERT INTO membresia (tipo, duracion, valor, fecha_inicio, fecha_fin,usuario_id) VALUES
-- ('Nivel 0', 30, 0, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY),1),
-- ('Nivel 1', 60, 50, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 60 DAY),2),
-- ('Nivel 2', 90, 100, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 90 DAY),3);

-- Insertar datos de prueba en pesoRegistro
INSERT INTO pesoregistro (usuario_id, fecha, peso)
VALUES
    (1, '2024-01-01', 71.0),
    (1, '2024-01-07', 75.0),
    (1, '2024-01-14', 70.0),
    (1, '2024-01-29', 68.0),
    (1, '2024-02-01', 70.5),
    (1, '2024-03-01', 70.0),
    (2, '2024-01-01', 76.0),
    (2, '2024-02-01', 75.5),
    (2, '2024-03-01', 75.0),
    (3, '2024-01-01', 61.0),
    (3, '2024-02-01', 60.5),
    (3, '2024-03-01', 60.0);

INSERT INTO articulo (titulo, subtitulo, imagen, contenido, tipoEntrenamiento) VALUES
                                                                                   ('Guía Completa para el Entrenamiento de Fuerza', 'Mejora tu fuerza y masa muscular con estos consejos', '/img/ejercicioDos.PNG', 'El entrenamiento de fuerza es esencial para desarrollar músculo y mejorar tu salud general. Comienza con ejercicios básicos como el press de banca, sentadillas y peso muerto. Asegúrate de aumentar gradualmente el peso para evitar lesiones. Mantén una técnica adecuada y enfócate en la forma correcta durante cada repetición. No te olvides de incluir un calentamiento adecuado antes de cada sesión y de enfriar después del entrenamiento para facilitar la recuperación muscular y prevenir lesiones.', 'Musculacio	n'),

                                                                                   ('Beneficios del Entrenamiento con Pesas', 'Más allá de los músculos: mejora tu salud', '/img/ejercicioUno.PNG', 'Levantar pesas no solo aumenta la masa muscular, sino que también mejora la densidad ósea, acelera el metabolismo y reduce el riesgo de enfermedades crónicas. Incorpora pesas en tu rutina para ver estos beneficios. Además, el entrenamiento con pesas puede mejorar tu estado de ánimo y aumentar tu energía diaria, haciéndote sentir más fuerte y seguro.', 'Musculacion'),

                                                                                   ('Rutina de Hipertrofia para Principiantes', 'Cómo empezar en el mundo de la musculación', '/img/ejercicioTres.PNG', 'Si eres nuevo en la musculación, una rutina de hipertrofia es ideal para ganar músculo. Enfócate en ejercicios compuestos y realiza de 3 a 4 series de 8 a 12 repeticiones. No olvides calentar antes de entrenar y estirar después. Con el tiempo, podrás aumentar el peso y la intensidad de tus entrenamientos, asegurándote de progresar de manera segura y efectiva.', 'Musculacion'),

                                                                                   ('Entrenamiento de Cuerpo Completo', 'Ejercicios efectivos para trabajar todos los músculos', '/img/ejercicioCuatro.JPG', 'Una rutina de cuerpo completo es excelente para aquellos con tiempo limitado. Incluye ejercicios como el press militar, dominadas y remo con barra. Realiza esta rutina 3 veces por semana para obtener mejores resultados. Además, asegúrate de descansar adecuadamente entre sesiones para permitir que tus músculos se recuperen y crezcan.', 'Musculacion'),

                                                                                   ('Importancia del Descanso en la Musculación', 'Por qué tu cuerpo necesita recuperarse', '/img/ejercicioCinco.JPG', 'El descanso es crucial para el crecimiento muscular. Asegúrate de dormir al menos 7-8 horas por noche y toma días de descanso entre sesiones intensas de entrenamiento para permitir la recuperación muscular. Sin un descanso adecuado, tus músculos no tendrán la oportunidad de repararse y crecer, lo que podría llevar a lesiones y fatiga.', 'Musculacion'),

                                                                                   ('Cómo Evitar Estancarte en el Gimnasio', 'Mantén tus progresos en ascenso', '/img/ejercicioDos.PNG', 'Si sientes que te has estancado, prueba a variar tus ejercicios, aumenta la intensidad y asegúrate de comer adecuadamente. La sobrecarga progresiva es clave para seguir avanzando en tus objetivos. Mantén un registro de tus entrenamientos y haz ajustes regularmente para asegurarte de que estás desafiando a tus músculos de nuevas maneras.', 'Musculacion'),

                                                                                   ('Nutrición para el Crecimiento Muscular', 'Qué comer para maximizar tus ganancias', '/img/ejercicioCinco.JPG', 'Una dieta rica en proteínas, carbohidratos y grasas saludables es esencial para el crecimiento muscular. Consume alimentos como pollo, arroz integral, aguacates y batidos de proteínas para apoyar tus entrenamientos. Además, asegúrate de mantenerte bien hidratado y de comer una variedad de alimentos para obtener todos los nutrientes necesarios.', 'Musculacion'),

                                                                                   ('Suplementos para la Musculación', 'Lo que realmente necesitas', '/img/ejercicioTres.PNG', 'Los suplementos pueden ser útiles, pero no son necesarios para todos. La proteína en polvo, la creatina y los BCAA son populares entre los culturistas. Consulta con un nutricionista antes de comenzar a tomar cualquier suplemento para asegurarte de que sean adecuados para tus necesidades específicas.', 'Musculacion'),

                                                                                   ('Entrenamiento de Alta Intensidad (HIIT) para la Musculación', 'Aumenta la intensidad y maximiza los resultados', '/img/ejercicioTres.PNG', 'Incorporar sesiones de HIIT en tu rutina puede mejorar tu resistencia y acelerar la quema de grasa. Combina ejercicios de fuerza con ráfagas cortas de cardio intenso para obtener los beneficios. Este tipo de entrenamiento también puede ayudarte a ahorrar tiempo, ya que las sesiones de HIIT son generalmente más cortas pero más efectivas.', 'Musculacion'),

                                                                                   ('Consejos para Levantar Pesas de Forma Segura', 'Evita lesiones y mejora tu técnica', '/img/ejercicioUno.PNG', 'La técnica correcta es crucial para evitar lesiones. Mantén la espalda recta, usa un cinturón de levantamiento si es necesario y no dudes en pedir ayuda si estás levantando pesos pesados. Además, asegúrate de calentar adecuadamente antes de cada sesión de levantamiento y de enfriar después para reducir el riesgo de lesiones.', 'Musculacion'),

                                                                                   ('Rutina de Entrenamiento para Definición Muscular', 'Cómo lograr un cuerpo definido', '/img/ejercicioDos.PNG', 'Para definir los músculos, combina entrenamiento de fuerza con cardio y una dieta baja en calorías. Realiza series más largas con menos peso y reduce tu ingesta calórica para quemar grasa corporal. Mantén una dieta equilibrada y asegúrate de mantener un déficit calórico para ver resultados en la definición muscular.', 'Musculacion'),

                                                                                   ('La Importancia del Calentamiento y Enfriamiento', 'Cómo preparar tu cuerpo para el entrenamiento', '/img/ejercicioCuatro.JPG', 'El calentamiento prepara tus músculos para el esfuerzo y reduce el riesgo de lesiones. Dedica 10 minutos a actividades ligeras como estiramientos dinámicos antes de empezar tu rutina de entrenamiento. Después de entrenar, realiza estiramientos estáticos para ayudar a la recuperación muscular y reducir la rigidez.', 'Musculacion'),

                                                                                   ('Mejores Ejercicios para Pecho', 'Desarrolla un pecho fuerte y definido', '/img/ejercicioCuatro.JPG', 'El press de banca es uno de los mejores ejercicios para desarrollar el pecho. También puedes incluir variaciones como el press inclinado y el press declinado para trabajar diferentes áreas del pecho. No olvides complementar con ejercicios como aperturas con mancuernas y fondos en paralelas.', 'Musculacion'),

                                                                                   ('Cómo Mejorar tus Sentadillas', 'Técnicas y consejos para un mejor rendimiento', '/img/ejercicioTres.PNG', 'La sentadilla es un ejercicio fundamental para fortalecer las piernas y glúteos. Asegúrate de mantener una postura adecuada, con la espalda recta y las rodillas alineadas con los pies. Practica la sentadilla profunda para maximizar la activación muscular.', 'Musculacion'),

                                                                                   ('Entrenamiento con Pesas para Mujeres', 'Rompiendo mitos y obteniendo resultados', '/img/ejercicioCuatro.JPG', 'El entrenamiento con pesas no hará que las mujeres se vuelvan musculosas de forma exagerada, sino que les ayudará a tonificar y fortalecer su cuerpo. Las mujeres pueden beneficiarse de levantamientos compuestos como el peso muerto y el press de banca. La clave está en la consistencia y en seguir una rutina bien estructurada.', 'Musculacion'),
                                                                                   ('Guía Completa para el Entrenamiento de Calistenia', 'Mejora tu fuerza y resistencia con estos ejercicios', '/img/calistenia1.PNG', 'El entrenamiento de calistenia utiliza el peso corporal para desarrollar fuerza, resistencia y flexibilidad. Ejercicios como flexiones, dominadas, fondos y planchas son fundamentales. Asegúrate de progresar gradualmente aumentando la dificultad y variando los ejercicios. Incluye también ejercicios de movilidad y flexibilidad para mantener la salud articular.', 'Calistenia'),
                                                                                   ('Rutina Avanzada de Calistenia', 'Supera tus límites con esta rutina avanzada', '/img/calistenia2.PNG', 'Esta rutina avanzada de calistenia combina ejercicios de alta intensidad como muscle-ups, planchas avanzadas y handstand push-ups. Es importante mantener una técnica impecable y escuchar tu cuerpo para evitar lesiones. Incluye ejercicios de precalentamiento y estiramientos para preparar y recuperar los músculos.', 'Calistenia'),
                                                                                   ('Cómo Empezar con Calistenia', 'Guía para principiantes en calistenia', '/img/calistenia3.PNG', 'Si eres nuevo en la calistenia, comienza con ejercicios básicos como flexiones de rodillas, fondos en banco y dominadas asistidas. A medida que ganas fuerza y confianza, progresa hacia ejercicios más desafiantes. Mantén una rutina regular y ajusta la intensidad según tus capacidades.', 'Calistenia'),
                                                                                   ('Beneficios de la Calistenia', 'Descubre por qué la calistenia es tan efectiva', '/img/calistenia4.PNG', 'La calistenia no solo mejora la fuerza muscular, sino también la resistencia cardiovascular y la flexibilidad. Es una forma versátil de entrenamiento que puede adaptarse a diferentes niveles de condición física y objetivos personales. Explora los beneficios y comienza tu viaje hacia una mayor salud y bienestar.', 'Calistenia'),
                                                                                   ('Ejercicios Básicos de Calistenia', 'Fundamentos para principiantes', '/img/calistenia5.PNG', 'Aprende los ejercicios básicos de calistenia que forman la base de cualquier entrenamiento efectivo: flexiones, dominadas, fondos y planchas. Estos movimientos te ayudarán a desarrollar fuerza, coordinación y control corporal. Sigue una progresión adecuada y mantén una técnica correcta en cada repetición.', 'Calistenia'),
                                                                                   ('Entrenamiento de Calistenia en Casa', 'Rutina efectiva sin equipo especializado', '/img/calistenia1.PNG', 'Realiza una rutina de calistenia en casa utilizando tu propio peso corporal. Ejercicios como sentadillas, flexiones y fondos en sillas son excelentes opciones para fortalecer músculos clave sin necesidad de equipo costoso. Incluye también ejercicios de estiramiento para mejorar la flexibilidad y prevenir lesiones.', 'Calistenia'),
                                                                                   ('Calistenia para Mujeres', 'Entrenamiento adaptado a las necesidades femeninas', '/img/calistenia2.PNG', 'Descubre cómo la calistenia puede beneficiar específicamente a las mujeres, fortaleciendo el core, tonificando los músculos y mejorando la flexibilidad. Incorpora variaciones como las flexiones modificadas y los ejercicios de piernas para obtener resultados efectivos y duraderos.', 'Calistenia'),
                                                                                   ('Rutina de Calistenia en Parques', 'Aprovecha los espacios públicos para entrenar', '/img/calistenia3.PNG', 'Utiliza parques y áreas públicas para realizar tu rutina de calistenia al aire libre. Ejercicios como barras para dominadas, bancos para fondos y bancos bajos para flexiones son ideales para aprovechar el entorno urbano y mantener un estilo de vida activo.', 'Calistenia'),
                                                                                   ('Calistenia Avanzada para Desarrollar Músculo', 'Ejercicios intensos para crecimiento muscular', '/img/calistenia4.PNG', 'Enfócate en ejercicios avanzados de calistenia como dominadas con peso, fondos lastrados y planchas con una sola mano para desafiar y estimular el crecimiento muscular. Asegúrate de complementar con una dieta adecuada y suficiente descanso para maximizar los resultados.', 'Calistenia'),
                                                                                   ('Cómo Planificar tu Entrenamiento de Calistenia', 'Consejos para estructurar tu rutina', '/img/calistenia5.PNG', 'Organiza tu entrenamiento de calistenia estableciendo metas claras, variando ejercicios y progresando gradualmente. Incorpora días de descanso y actividades de recuperación activa para optimizar el rendimiento y reducir el riesgo de lesiones.', 'Calistenia'),
                                                                                   ('Beneficios Mentales de la Calistenia', 'Ejercicio físico para una mente más fuerte', '/img/calistenia1.PNG', 'Además de fortalecer el cuerpo, la calistenia puede mejorar el estado de ánimo, reducir el estrés y aumentar la concentración. Descubre cómo el ejercicio regular puede tener un impacto positivo en tu bienestar mental y emocional.', 'Calistenia'),
                                                                                   ('Calistenia para Corredores', 'Fortalecimiento complementario para corredores', '/img/calistenia2.PNG', 'Los corredores pueden beneficiarse enormemente de la calistenia para fortalecer músculos específicos, mejorar la resistencia y prevenir lesiones comunes. Incluye ejercicios de equilibrio, fuerza y flexibilidad en tu rutina para un rendimiento óptimo y una recuperación más rápida.', 'Calistenia'),
                                                                                   ('Calistenia y Flexibilidad', 'Cómo mejorar la movilidad con ejercicios simples', '/img/calistenia3.PNG', 'La calistenia no solo desarrolla fuerza, sino que también mejora la flexibilidad y la movilidad articular. Incorpora ejercicios dinámicos como estocadas, giros de torso y movimientos de brazos para mantener una amplitud de movimiento óptima y reducir el riesgo de lesiones.', 'Calistenia'),
                                                                                   ('Ejercicios de Calistenia para Todo el Cuerpo', 'Trabajo integral de los principales grupos musculares', '/img/calistenia4.PNG', 'Realiza una rutina completa de calistenia que abarque todos los grupos musculares principales, incluyendo ejercicios para piernas, glúteos, espalda, pecho y brazos. Mantén un equilibrio entre ejercicios de empuje y tracción para un desarrollo muscular uniforme y eficaz.', 'Calistenia'),
                                                                                   ('Calistenia y Alimentación Saludable', 'Cómo combinar ejercicio y nutrición adecuada', '/img/calistenia5.PNG', 'La nutrición juega un papel crucial en el rendimiento y la recuperación en la calistenia. Consumir una dieta equilibrada y adecuada en calorías, proteínas, carbohidratos y grasas saludables es esencial para apoyar tu entrenamiento y alcanzar tus objetivos de fitness.', 'Calistenia'),
                                                                                   ('Guía Completa para el Entrenamiento Cardiovascular', 'Mejora tu resistencia y salud cardiovascular', '/img/cardio1.PNG', 'El entrenamiento cardiovascular incluye actividades como correr, nadar, andar en bicicleta y saltar la cuerda. Estas actividades ayudan a fortalecer el corazón y los pulmones, mejorar la circulación sanguínea y aumentar la resistencia general. Varía tu rutina para mantener el interés y maximizar los beneficios.', 'Cardio'),
                                                                                   ('Rutina de Intervalos de Alta Intensidad (HIIT)', 'Quema calorías y mejora tu condición física con HIIT', '/img/cardio2.PNG', 'El entrenamiento HIIT alterna períodos cortos de ejercicio intenso con períodos de descanso o actividad de baja intensidad. Este tipo de entrenamiento es efectivo para quemar calorías, mejorar la resistencia y acelerar el metabolismo. Incorpora ejercicios como sprints, saltos y burpees para un entrenamiento completo.', 'Cardio'),
                                                                                   ('Entrenamiento Cardiovascular en Casa', 'Ejercicios efectivos para hacer en casa', '/img/cardio3.PNG', 'Si prefieres entrenar en casa, puedes realizar ejercicios cardiovasculares como jumping jacks, escaladores y sentadillas con salto. Estos ejercicios no requieren equipo especializado y te permiten mantener tu salud cardiovascular sin salir de casa. Combínalos con ejercicios de fuerza para un programa de entrenamiento completo.', 'Cardio'),
                                                                                   ('Beneficios del Entrenamiento Cardiovascular', 'Descubre por qué el cardio es crucial para la salud', '/img/cardio4.PNG', 'El entrenamiento cardiovascular no solo mejora la salud del corazón y los pulmones, sino que también ayuda a controlar el peso, reduce el estrés y mejora el estado de ánimo. Incorpora sesiones regulares de cardio en tu rutina para mantener un estilo de vida activo y saludable.', 'Cardio'),
                                                                                   ('Cómo Empezar con Entrenamiento Cardiovascular', 'Guía para principiantes en ejercicios cardiovasculares', '/img/cardio5.PNG', 'Si eres nuevo en el entrenamiento cardiovascular, comienza con actividades de baja intensidad como caminar o andar en bicicleta. A medida que aumenta tu resistencia, puedes incorporar actividades más intensas como correr o nadar. Mantén una frecuencia cardíaca moderada y escucha tu cuerpo durante el ejercicio.', 'Cardio'),
                                                                                   ('Cardio y Pérdida de Peso', 'Cómo utilizar el cardio para quemar grasa', '/img/cardio1.PNG', 'El entrenamiento cardiovascular es efectivo para quemar calorías y acelerar la pérdida de peso. Incorpora ejercicios como correr, nadar o usar máquinas de cardio en el gimnasio para mantener un déficit calórico y alcanzar tus objetivos de peso. Combina con una dieta saludable para mejores resultados.', 'Cardio'),
                                                                                   ('Ejercicios Cardiovasculares para Tonificar', 'Mejora la definición muscular con cardio', '/img/cardio2.PNG', 'Utiliza ejercicios cardiovasculares como el ciclismo, la natación y los ejercicios de escaleras para tonificar y esculpir los músculos. Mantén una intensidad moderada a alta y varía tu rutina para desafiar diferentes grupos musculares. Incluye también ejercicios de fuerza para un entrenamiento equilibrado.', 'Cardio'),
                                                                                   ('Cardio y Salud Mental', 'Beneficios para el bienestar emocional', '/img/cardio3.PNG', 'El cardio no solo mejora la salud física, sino que también tiene beneficios significativos para la salud mental. Reduce el estrés, mejora el estado de ánimo y promueve una sensación general de bienestar. Realiza sesiones regulares de cardio para mantener una mente sana y equilibrada.', 'Cardio'),
                                                                                   ('Entrenamiento de Resistencia Cardiovascular', 'Cómo mejorar la resistencia con ejercicios cardio', '/img/cardio4.PNG', 'Para mejorar la resistencia cardiovascular, incorpora ejercicios como correr largas distancias, nadar largos tramos o participar en clases de aeróbicos. Aumenta gradualmente la duración e intensidad de tus sesiones para desafiar tu capacidad aeróbica y mejorar la eficiencia del corazón y los pulmones.', 'Cardio'),
                                                                                   ('Cardio para Todos los Niveles', 'Actividades cardiovasculares para principiantes y avanzados', '/img/cardio5.PNG', 'El entrenamiento cardiovascular es accesible para personas de todos los niveles de condición física. Comienza con actividades suaves como caminar o andar en bicicleta y avanza hacia opciones más intensas según tu capacidad y objetivos. Mantén un enfoque gradual y seguro para evitar lesiones.', 'Cardio'),
                                                                                   ('Beneficios del HIIT en el Cardio', 'Mejora rápida de la resistencia y quema de grasa', '/img/cardio1.PNG', 'El HIIT es una forma efectiva de entrenamiento cardiovascular que combina ráfagas cortas de ejercicio intenso con períodos de descanso. Este método aumenta la capacidad aeróbica, mejora la quema de grasa y acelera el metabolismo. Integra sesiones regulares de HIIT en tu rutina para obtener resultados rápidos y duraderos.', 'Cardio'),
                                                                                   ('Ejercicios de Cardio en Grupo', 'Motivación y diversión con ejercicios en grupo', '/img/cardio2.PNG', 'Participa en clases de cardio en grupo como zumba, spinning o clases de aeróbicos para mejorar la motivación y disfrutar del ejercicio en compañía. Estas clases son ideales para mantener la motivación y la consistencia en tu rutina de entrenamiento cardiovascular.', 'Cardio'),
                                                                                   ('Cardio y Envejecimiento Saludable', 'Beneficios del ejercicio cardiovascular para mayores', '/img/cardio3.PNG', 'El ejercicio cardiovascular regular ayuda a mantener la salud cardiovascular, la densidad ósea y la movilidad en la edad adulta y avanzada. Realiza ejercicios como caminar, nadar o hacer ejercicios de bajo impacto para mejorar la calidad de vida y la longevidad.', 'Cardio'),
                                                                                   ('Cardio y Flexibilidad', 'Mejora de la movilidad y la elasticidad muscular', '/img/cardio4.PNG', 'El entrenamiento cardiovascular puede mejorar la flexibilidad y la movilidad articular, especialmente cuando se combina con ejercicios de estiramiento. Incorpora ejercicios como yoga o pilates junto con tus sesiones de cardio para maximizar los beneficios físicos y mentales.', 'Cardio'),
                                                                                   ('Cardio y Reducción del Estrés', 'Cómo el ejercicio cardiovascular ayuda a gestionar el estrés', '/img/cardio5.PNG', 'El ejercicio cardiovascular libera endorfinas que mejoran el estado de ánimo y reducen los niveles de estrés. Incorpora actividades como correr, nadar o practicar deportes aeróbicos para aliviar tensiones y mejorar tu bienestar emocional.', 'Cardio');