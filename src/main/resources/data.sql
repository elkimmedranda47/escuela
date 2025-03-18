-- Insertar 10 personas
INSERT INTO personas (nombre, apellido, fecha_nacimiento, email, telefono, tipo, password) VALUES
('Juan', 'Pérez', '1995-05-10', 'juan.perez@email.com', '5551234567', 'ESTUDIANTE', 'hashed_password_1'),
('María', 'Gómez', '1980-07-20', 'maria.gomez@email.com', '5557654321', 'PROFESOR', 'hashed_password_2'),
('Carlos', 'Díaz', '1990-12-15', 'carlos.diaz@email.com', '5559876543', 'ADMINISTRATIVO', 'hashed_password_3'),
('Ana', 'López', '1998-03-22', 'ana.lopez@email.com', '5551122334', 'ESTUDIANTE', 'hashed_password_4'),
('Luis', 'Rodríguez', '1975-11-05', 'luis.rodriguez@email.com', '5554455667', 'PROFESOR', 'hashed_password_5'),
('Sofía', 'Martínez', '1988-09-18', 'sofia.martinez@email.com', '5557788990', 'ADMINISTRATIVO', 'hashed_password_6'),
('Pedro', 'Sánchez', '2000-06-01', 'pedro.sanchez@email.com', '5552233445', 'ESTUDIANTE', 'hashed_password_7'),
('Laura', 'Fernández', '1982-04-28', 'laura.fernandez@email.com', '5555566778', 'PROFESOR', 'hashed_password_8'),
('Diego', 'Ruiz', '1993-01-12', 'diego.ruiz@email.com', '5558899001', 'ADMINISTRATIVO', 'hashed_password_9'),
('Elena', 'Jiménez', '1979-10-08', 'elena.jimenez@email.com', '5553344556', 'ESTUDIANTE', 'hashed_password_10'),
('Rodríguez', 'Carlos', '1985-11-08', 'carlos.rodriguez@example.com', '+34600987654', 'ADMINISTRATIVO', '$2a$10$wTqj8suFiQx5fxRpfZxe8uhbsoYyp3ZNJ6ObDTAIQBoM4TZoe/Pii');

-- Insertar 3 estudiantes
INSERT INTO estudiantes (id_persona, numero_matricula, grado) VALUES
(1, 'MAT-2024001', 'Ingeniería Civil'),
(4, 'MAT-2024002', 'Arquitectura'),
(7, 'MAT-2024003', 'Medicina');

-- Insertar 3 profesores
INSERT INTO profesores (id_persona, especialidad, fecha_contratacion) VALUES
(2, 'Física', '2012-08-15'),
(5, 'Química', '2008-05-20'),
(8, 'Biología', '2015-11-01');

-- Insertar 3 administrativos
INSERT INTO administrativos (id_persona, cargo, departamento) VALUES
(3, 'Director de Admisiones', 'Departamento de Admisiones'),
(6, 'Secretario Académico', 'Departamento de Estudios'),
(9, 'Tesorero', 'Departamento de Finanzas');

-- Insertar 3 cursos
INSERT INTO cursos (nombre, descripcion, creditos, id_profesor) VALUES
('Física I', 'Mecánica clásica y termodinámica', 3, 2),
('Química Orgánica', 'Estructura y reactividad de compuestos orgánicos', 3, 5),
('Biología Celular', 'Estructura y función de la célula', 3, 8);

-- Insertar 3 matrículas
INSERT INTO matricula (id_estudiante, id_curso, fecha_matricula) VALUES
(1, 1, '2024-03-15'),
(4, 2, '2024-03-15'),
(7, 3, '2024-03-15');