INSERT INTO students (dni, first_name, last_name, birth_date, has_scholarship)
VALUES ('10000001A', 'Juan', 'Pérez Gómez', '2003-05-14', true),
       ('10000002B', 'María', 'López Sánchez', '2004-02-03', true),
       ('10000003C', 'Antonio', 'Martín Ruiz', '2002-11-21', true),
       ('10000004D', 'Laura', 'García Fernández', '2003-07-09', true),
       ('10000005E', 'Carlos', 'Sánchez Molina', '2001-09-30', true),
       ('10000006F', 'Ana', 'Romero Díaz', '2004-01-18', true),
       ('10000007G', 'David', 'Navarro Torres', '2002-04-26', true),
       ('10000008H', 'Lucía', 'Ortega Ramos', '2003-12-05', true),
       ('10000009J', 'Jorge', 'Molina Cruz', '2001-06-11', true),
       ('10000010K', 'Paula', 'Gil Moreno', '2004-08-22', true),
       ('10000011L', 'Sergio', 'Hernández Vega', '2002-10-02', true),
       ('10000012M', 'Claudia', 'Castro León', '2003-03-17', true),
       ('10000013N', 'Iván', 'Prieto Blanco', '2001-01-29', true),
       ('10000014P', 'Natalia', 'Suárez Campos', '2004-06-07', true),
       ('10000015Q', 'Rubén', 'Iglesias Fuentes', '2002-09-14', true),
       ('10000016R', 'Elena', 'Cabrera Nieto', '2003-11-25', true),
       ('10000017S', 'Miguel', 'Vargas Pardo', '2001-04-08', true),
       ('10000018T', 'Sara', 'Méndez Ríos', '2004-02-19', true),
       ('10000019U', 'Álvaro', 'Rey Lorenzo', '2002-07-01', true),
       ('10000020V', 'Irene', 'Flores Bautista', '2003-10-12', true);


insert into departments(name, location)
values ('Informática y comunicaciones', 'Planta baja'),
       ('Administración', 'Segunda planta'),
       ('FOL', 'Planta baja'),
       ('Comercio', 'Primera planta');

INSERT INTO teachers (birth_date, start_date, email, first_name, last_name, department_id)
VALUES ('1980-03-15', '2010-09-01', 'juan.perez@centro.edu', 'Juan', 'Pérez', 1),
       ('1975-07-22', '2005-09-01', 'maria.gomez@centro.edu', 'María', 'Gómez', 2),
       ('1988-01-10', '2015-09-01', 'carlos.lopez@centro.edu', 'Carlos', 'López', 3),
       ('1982-11-05', '2012-09-01', 'ana.martin@centro.edu', 'Ana', 'Martín', 1),
       ('1979-06-30', '2008-09-01', 'david.sanchez@centro.edu', 'David', 'Sánchez', 2),
       ('1990-04-18', '2018-09-01', 'laura.romero@centro.edu', 'Laura', 'Romero', 3),
       ('1985-09-12', '2013-09-01', 'jorge.navarro@centro.edu', 'Jorge', 'Navarro', 4),
       ('1978-02-27', '2006-09-01', 'patricia.ruiz@centro.edu', 'Patricia', 'Ruiz', 4),
       ('1983-12-03', '2011-09-01', 'alberto.molina@centro.edu', 'Alberto', 'Molina', 1),
       ('1992-08-19', '2020-09-01', 'silvia.ortega@centro.edu', 'Silvia', 'Ortega', 1);


INSERT INTO modules (module_id, name, description)
VALUES ('DAM-PROG', 'Programación',
        'Desarrollo de aplicaciones utilizando programación orientada a objetos, estructuras de datos y control de flujo.'),
       ('DAM-BD', 'Bases de Datos', 'Diseño y gestión de bases de datos relacionales, lenguaje SQL y normalización.'),
       ('DAM-ED', 'Entornos de Desarrollo',
        'Uso de herramientas de desarrollo, control de versiones y pruebas de software.'),
       ('DAM-SI', 'Sistemas Informáticos',
        'Instalación, configuración y mantenimiento de sistemas operativos y redes.'),
       ('DAM-PSP', 'Programación de Servicios y Procesos',
        'Desarrollo de aplicaciones concurrentes, multiproceso y servicios en red.'),
       ('DAM-PMDM', 'Programación Multimedia y Dispositivos Móviles',
        'Desarrollo de aplicaciones móviles y uso de recursos multimedia.'),
       ('DAM-AD', 'Acceso a Datos', 'Persistencia de datos, uso de ficheros, bases de datos y ORM.'),
       ('DAM-DI', 'Desarrollo de Interfaces',
        'Diseño e implementación de interfaces gráficas de usuario usables y accesibles.'),
       ('DAM-FOL', 'Formación y Orientación Laboral',
        'Derechos laborales, prevención de riesgos y orientación profesional.'),
       ('DAM-EIE', 'Empresa e Iniciativa Emprendedora', 'Creación y gestión de empresas y proyectos emprendedores.');

INSERT INTO modules (module_id, name, description)
VALUES ('DAW-PROG', 'Programación', 'Fundamentos de programación y desarrollo de aplicaciones utilizando Java y POO.'),
       ('DAW-BD', 'Bases de Datos', 'Diseño, creación y consulta de bases de datos mediante SQL.'),
       ('DAW-ED', 'Entornos de Desarrollo',
        'Herramientas de desarrollo, control de versiones y metodologías de trabajo.'),
       ('DAW-SI', 'Sistemas Informáticos', 'Administración de sistemas operativos, redes y servicios.'),
       ('DAW-LM', 'Lenguajes de Marcas y Sistemas de Gestión de Información',
        'HTML, CSS, XML y tecnologías asociadas para la web.'),
       ('DAW-DWEC', 'Desarrollo Web en Entorno Cliente',
        'Programación en el lado cliente con JavaScript y frameworks.'),
       ('DAW-DWES', 'Desarrollo Web en Entorno Servidor', 'Desarrollo de aplicaciones web dinámicas en el servidor.'),
       ('DAW-DAW', 'Despliegue de Aplicaciones Web', 'Publicación, configuración y mantenimiento de aplicaciones web.'),
       ('DAW-FOL', 'Formación y Orientación Laboral',
        'Prevención de riesgos, legislación laboral y orientación profesional.'),
       ('DAW-EIE', 'Empresa e Iniciativa Emprendedora', 'Gestión empresarial y desarrollo de proyectos emprendedores.');

INSERT INTO stu_mod (student_id, module_id)
VALUES
-- DAM (students 1–10)
(1, 'DAM-PROG'),
(1, 'DAM-BD'),
(1, 'DAM-ED'),

(2, 'DAM-PROG'),
(2, 'DAM-SI'),
(2, 'DAM-FOL'),

(3, 'DAM-PROG'),
(3, 'DAM-BD'),
(3, 'DAM-AD'),

(4, 'DAM-DI'),
(4, 'DAM-PMDM'),

(5, 'DAM-PSP'),
(5, 'DAM-AD'),
(5, 'DAM-EIE'),

(6, 'DAM-PROG'),
(6, 'DAM-DI'),

(7, 'DAM-BD'),
(7, 'DAM-SI'),
(7, 'DAM-ED'),

(8, 'DAM-PMDM'),

(9, 'DAM-FOL'),
(9, 'DAM-EIE'),

(10, 'DAM-PROG'),

-- DAW (students 11–18)
(11, 'DAW-PROG'),
(11, 'DAW-BD'),
(11, 'DAW-LM'),

(12, 'DAW-LM'),
(12, 'DAW-DWEC'),

(13, 'DAW-PROG'),
(13, 'DAW-DWES'),
(13, 'DAW-DAW'),

(14, 'DAW-BD'),
(14, 'DAW-SI'),

(15, 'DAW-DWEC'),
(15, 'DAW-DWES'),

(16, 'DAW-FOL'),

(17, 'DAW-PROG'),
(17, 'DAW-EIE'),

(18, 'DAW-LM');
