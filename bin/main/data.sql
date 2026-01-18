-- Las tablas ya están creadas por Hibernate antes de ejecutar este script

-- Plantas de reciclaje
INSERT INTO planta_reciclaje (id, nombre, ubicacion, maxima_capacidad, codigo_postal, tipo_servidor, url_base, puerto)
VALUES
('1', 'Planta Donosti', 'Donosti', 10000, 30303, 'PLASSB', 'http://localhost:8081/plassb', NULL),
('2', 'Planta Bilbao', 'Bilbao', 8000, 48001, 'CONTSOCKET', 'localhost', 9000);

-- Empleados
INSERT INTO empleado (id, nombre, email, password)
VALUES
('EMP001', 'Ana García', 'ana@ecoembes.com', '1234'),
('EMP002', 'Carlos López', 'carlos@ecoembes.com', '1234'),
('EMP003', 'María Rodríguez', 'maria@ecoembes.com', '1234');

-- Contenedores Donosti (30303)
INSERT INTO contenedor (id, ubicacion, capacidad, codigo_postal, nivel_llenado, num_envases)
VALUES
('CNT_DN_001', 'Plaza Gipuzkoa 1', 1000, 30303, 850, 170),
('CNT_DN_002', 'Calle San Martín 45', 1000, 30303, 600, 120),
('CNT_DN_003', 'Avenida Libertad 89', 1000, 30303, 750, 150),
('CNT_DN_004', 'Boulevard Zurriola 12', 1000, 30303, 400, 80);

-- Contenedores Bilbao (48001)
INSERT INTO contenedor (id, ubicacion, capacidad, codigo_postal, nivel_llenado, num_envases)
VALUES
('CNT_BB_001', 'Gran Vía 23', 1000, 48001, 900, 180),
('CNT_BB_002', 'Plaza Circular 8', 1000, 48001, 650, 130),
('CNT_BB_003', 'Calle Licenciado Poza 34', 1000, 48001, 550, 110),
('CNT_BB_004', 'Alameda Rekalde 15', 1000, 48001, 300, 60);

-- Registros de uso (historial)
INSERT INTO registro_uso_contenedor (id, contenedor_id, fecha, nivel_llenado, num_envases)
VALUES
('REG001', 'CNT_DN_001', TIMESTAMP '2025-01-15 10:00:00', 500, 100),
('REG002', 'CNT_DN_001', TIMESTAMP '2025-01-16 10:00:00', 700, 140),
('REG003', 'CNT_DN_001', TIMESTAMP '2025-01-17 10:00:00', 850, 170),
('REG004', 'CNT_DN_002', TIMESTAMP '2025-01-15 10:00:00', 300, 60),
('REG005', 'CNT_DN_002', TIMESTAMP '2025-01-16 10:00:00', 450, 90),
('REG006', 'CNT_DN_002', TIMESTAMP '2025-01-17 10:00:00', 600, 120),
('REG007', 'CNT_BB_001', TIMESTAMP '2025-01-15 10:00:00', 600, 120),
('REG008', 'CNT_BB_001', TIMESTAMP '2025-01-16 10:00:00', 750, 150),
('REG009', 'CNT_BB_001', TIMESTAMP '2025-01-17 10:00:00', 900, 180),
('REG010', 'CNT_BB_002', TIMESTAMP '2025-01-15 10:00:00', 400, 80),
('REG011', 'CNT_BB_002', TIMESTAMP '2025-01-16 10:00:00', 500, 100),
('REG012', 'CNT_BB_002', TIMESTAMP '2025-01-17 10:00:00', 650, 130);