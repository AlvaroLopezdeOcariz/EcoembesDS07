DELETE FROM planta_reciclaje;
INSERT INTO planta_reciclaje 
(id, nombre, ubicacion, maxima_capacidad, codigo_postal, tipo_servidor, url_base, puerto)
VALUES 
('1', 'Planta Donosti', 'Donosti', 10000, 30303, 'PLASSB', 'http://localhost:8081/plassb', NULL);

INSERT INTO planta_reciclaje 
(id, nombre, ubicacion, maxima_capacidad, codigo_postal, tipo_servidor, url_base, puerto)
VALUES 
('2', 'Planta Bilbao', 'Bilbao', 8000, 48001, 'CONTSOCKET', 'http://localhost', 8083);