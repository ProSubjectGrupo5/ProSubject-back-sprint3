INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('admin','admin','0');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('alum1','alum1','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('alum2','alum2','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('alum3','alum3','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('alum4','alum4','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('prof1','prof1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('prof2','prof2','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('prof3','prof3','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('admin2','admin2','0');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('admin3','admin3','0');


INSERT INTO `administradores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Romero', 'Caceres', '4754620Y', 'antromcac@alum.us.es', 'Antonio', '603552741', '1');

INSERT INTO `administradores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('PruebaDNi', 'PruebaDNi', '475620Y', 'antromcac1@alum.us.es', 'Antonio', '603552741', '10');

INSERT INTO `administradores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('PruebaDNi', 'PruebaDNi', '4756201-Y', 'antromcac2@alum.us.es', 'Antonio', '603552741', '9');


INSERT INTO `universidades` (`nombre`) VALUES ('Universidad de Sevilla');
INSERT INTO `universidades` (`nombre`) VALUES ('Universidad Pablo de Olavide');


INSERT INTO `facultades` (`nombre`, `universidad_id`) VALUES ('Escuela Técnica Superior de Ingeniería Informática', '1');
INSERT INTO `facultades` (`nombre`, `universidad_id`) VALUES ('Arquitectura', '1');
INSERT INTO `facultades` (`nombre`, `universidad_id`) VALUES ('Matemática', '1');
INSERT INTO `facultades` (`nombre`, `universidad_id`) VALUES ('Historia del Arte', '2');
INSERT INTO `facultades` (`nombre`, `universidad_id`) VALUES ('Biología', '2');


INSERT INTO `grados` (`nombre`, `facultad_id`, `numerocursos`) VALUES ('Ingeniería del Software', '1', '4');
INSERT INTO `grados` (`nombre`, `facultad_id`, `numerocursos`) VALUES ('Ingeniería de Computadores', '1', '4');
INSERT INTO `grados` (`nombre`, `facultad_id`, `numerocursos`) VALUES ('Tecnología informática', '1', '4');
INSERT INTO `grados` (`nombre`, `facultad_id`, `numerocursos`) VALUES ('Ingeniería de la salud', '1', '4');
INSERT INTO `grados` (`nombre`, `facultad_id`, `numerocursos`) VALUES ('Historia del Arte contemporaneo', '4', '4');
INSERT INTO `grados` (`nombre`, `facultad_id`, `numerocursos`) VALUES ('Historia del Arte antiguo', '4', '4');


INSERT INTO `cursos` (`nombre`) VALUES ('PRIMERO');
INSERT INTO `cursos` (`nombre`) VALUES ('SEGUNDO');
INSERT INTO `cursos` (`nombre`) VALUES ('TERCERO');
INSERT INTO `cursos` (`nombre`) VALUES ('CUARTO');
INSERT INTO `cursos` (`nombre`) VALUES ('QUINTO');


INSERT INTO `asignaturas` (`nombre`, `curso_id`) VALUES ('Fundamentos de programación', '1');
INSERT INTO `asignaturas` (`nombre`, `curso_id`) VALUES ('Análisis y diseño de datos y algoritmo', '2');
INSERT INTO `asignaturas` (`nombre`, `curso_id`) VALUES ('Diseño y pruebas 1', '3');
INSERT INTO `asignaturas` (`nombre`, `curso_id`) VALUES ('Diseño y pruebas 2', '3');


INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('1', '1');
INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('1', '2');
INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('1', '3');
INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('2', '1');
INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('2', '4');
INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('3', '1');
INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('4', '1');


INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `useraccount_id`) VALUES ('Romero ', 'Organvidez','47543218R','drorganvidez@gmail.com', 'David', '6');
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `useraccount_id`) VALUES ('Rojas', 'Gutierrez','47546231T', 'rodrojgut@alum.us.es', 'Rodrigo', '7');
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `useraccount_id`) VALUES ('Carillo', 'Mirando', '47546221z','fcarmir@gmail.com', 'Fernando', '8');


INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Carillo', 'Mirando', '47546221Z', 'fcarmir@gmail.com', 'Fernando', '123456749', '2');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Rojas', 'Gutierrez', '47546231T', 'rodrojgut@alum.us.es', 'Rodrigo', '123456789', '3');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Romero', 'Caceres', '47546251Y', 'anaromcac@alum.us.es', 'Ana', '603552740', '4');


INSERT INTO `foros` (`titulo`) VALUES ('Foro Fundamentos de programación');
INSERT INTO `foros` (`titulo`) VALUES ('Foro Análisis y diseño de datos y algoritmo');
INSERT INTO `foros` (`titulo`) VALUES ('Foro Diseño y pruebas 1');
INSERT INTO `foros` (`titulo`) VALUES ('Foro Diseño y pruebas 2');


INSERT INTO `espacios` (`asignatura_id`, `foro_id`, `profesor_id`, `precio`) VALUES ('1', '1','1','8.0');
INSERT INTO `espacios` (`asignatura_id`, `foro_id`, `profesor_id`, `precio` ) VALUES ('2', '2','2','8.5');
INSERT INTO `espacios` (`asignatura_id`, `foro_id`, `profesor_id`, `precio`) VALUES ('3', '3','3','9.0');



INSERT INTO `horario` (`dia`,`fecha_inicio`,`fecha_fin`,`espacio_id`, `capacidad`) VALUES ('4','11:00:00','12:00:00','1',2);

INSERT INTO `horario` (`dia`,`fecha_inicio`,`fecha_fin`,`espacio_id`, `capacidad`)  VALUES ('5','12:00:00','13:00:00','1',6);

INSERT INTO `horario` (`dia`,`fecha_inicio`,`fecha_fin`,`espacio_id`, `capacidad`)  VALUES ('5','15:00:00','16:00:00','2',12);

INSERT INTO `horario_alumnos` (`horario_id`, `alumnos_id`) VALUES ('1', '1');
INSERT INTO `horario_alumnos` (`horario_id`, `alumnos_id`) VALUES ('1', '2');
INSERT INTO `horario_alumnos` (`horario_id`, `alumnos_id`) VALUES ('3', '3');







