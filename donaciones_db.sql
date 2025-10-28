create database donaciones_db;
use donaciones_db;

INSERT INTO albergues (nombre, descripcion, direccion, telefono, url_imagen) VALUES
('Albergue San Juan', 'Albergue que brinda atención a personas en situación de calle.', 'Av. Los Olivos 123, Lima', '+51 987654321', null),
('Hogar Esperanza', 'Proporciona refugio y alimentación a niños y adolescentes en riesgo.', 'Jr. Primavera 456, Lima', '+51 912345678', null),
('Centro de Acogida Luz y Vida', 'Albergue para adultos mayores en situación vulnerable.', 'Calle Los Pinos 789, Lima', '+51 998877665', null),
('Refugio La Familia', 'Ofrece apoyo integral a familias en situación de emergencia.', 'Av. Los Rosales 321, Lima', '+51 976543210', null),
('Albergue Camino de Luz', 'Albergue temporal para jóvenes en riesgo social.', 'Jr. Los Girasoles 654, Lima', '+51 911223344', null);

INSERT INTO ongs (nombre, descripcion, direccion, telefono, url_imagen) VALUES
('Fundación Esperanza', 'Brinda apoyo educativo y alimentación a niños en situación vulnerable.', 'Av. Libertad 123, Lima', '+51 987654321', NULL),
('Asociación Vida Sana', 'Promueve la salud integral y campañas de prevención en comunidades.', 'Jr. Salud 456, Lima', '+51 912345678', NULL),
('Manos Unidas', 'Ofrece asistencia a familias afectadas por desastres naturales.', 'Calle Los Pinos 789, Lima', '+51 998877665', NULL),
('Fundación Futuro', 'Apoya proyectos de desarrollo sostenible y capacitación laboral.', 'Av. Los Rosales 321, Lima', '+51 976543210', NULL),
('ONG Luz y Camino', 'Proporciona refugio y orientación a jóvenes en riesgo social.', 'Jr. Los Girasoles 654, Lima', '+51 911223344', NULL);

INSERT INTO usuarios (
    nombre,
    apellido,
    email,
    password,
    telefono,
    dni,
    ruc
)
VALUES (
    'Juan',
    'Pérez',
    'juan.perez@example.com',
    '123456',           -- contraseña de ejemplo (en tu app real se encripta)
    '987654321',
    '12345678',
    NULL                -- sin RUC porque es persona natural
);


INSERT INTO alimentos (
    nombre,
    descripcion,
    url_imagen
)
VALUES (
    'Arroz 1kg',
    'Paquete de arroz blanco de 1 kilogramo',
    'https://example.com/arroz.jpg'
);


INSERT INTO pedidos (
    alimento_id,
    donante_id,
    fecha_creacion,
    estado
)
VALUES (
    1,           -- id del alimento creado
    1,           -- id del usuario creado
    NOW(),
    'PENDIENTE'
);

DROP TABLE albergues;
select * from albergues;