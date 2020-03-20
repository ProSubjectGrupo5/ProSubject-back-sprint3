INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('antromcac','antromcac','0');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('alum1','alum1','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('rodrojgut','rodrojgut','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('anaromcac','anaromcac','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('prof4','prof4','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('drorganvidez','drorganvidez','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('rodrojgut1','rodrojgut1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('fcarmir1','fcarmir1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('lizesqsaa','lizesqsaa','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('josema_6497','josema_6497','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('crimaumit','crimaumit','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('alejandrocano','alejandrocano','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('martacantero','martacanterogarcia','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('andmecsan1','andmecsan1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('andmecsan','andmecsan','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('frajimsol1','frajimsol1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('frajimsol','frajimsol','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('eduardo1','eduardo1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('eduardo','eduardo','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('davidtenis1','davidtenis1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('davidtenis','davidtenis','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('rcbuenomolina1','rcbuenomolina1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('rcbuenomolina','rcbuenomolina','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('paulaguerrerofischer1','paulaguerrerofischer1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('paulaguerrerofischer','paulaguerrerofischer','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('javo136971','javo136971','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('javo13697','javo13697','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('fcarmir','fcarmir','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('saraaguadodelgado1','saraaguadodelgado1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('saraaguadodelgado','saraaguadodelgado','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('miriam1','miriam1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('miriam','miriam','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('marioogonzalez1','marioogonzalez1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('marioogonzalez','marioogonzalez','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('juanje_chus1','juanje_chus1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('juanje_chus','juanje_chus','2');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('carmenrucasti1','carmenrucasti1','1');
INSERT INTO `useraccounts` (`password`,`username`,`autoridad`) VALUES ('carmenrucasti','carmenrucasti','2');



INSERT INTO `administradores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Romero', 'Caceres', '4754620Y', 'antromcac@alum.us.es', 'Antonio', '603552740', '1');

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
INSERT INTO `asignaturas` (`nombre`, `curso_id`) VALUES ('Complemento de Bases de Datos', '4');


INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('1', '1');
INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('1', '2');
INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('1', '3');
INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('2', '1');
INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('2', '4');
INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('3', '1');
INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('4', '1');
INSERT INTO `asignaturas_grados` (`asignaturas_id`, `grados_id`) VALUES ('5', '1');

INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Prueba', 'Prueba','47543228A','profesorprueba@gmail.com', 'Prueba','123436385', '5', 0, 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Romero ', 'Organvidez','47543218R','drorganvidez@gmail.com', 'David', '123436485', '6', 0, '0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Rojas', 'Gutierrez','47546231T', 'rodrojgut@alum.us.es', 'Rodrigo', '123456789',  '7', 0, 0');
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Carillo', 'Mirando', '47546221Z','fcarmir@gmail.com', 'Fernando','652314129',  '8', 0, 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Cano', 'Caldero', '47546227Z','alejandrocano.caldero@gmail.com', 'Alejandro','123426719', '12', 0, 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Cantero', 'Garcia', '47546347A','martacanterogarcia@gmail.com', 'Marta', '127656283','13', 0, 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Meca', 'Sanchez', '47546343S','andmecsan@alum.us.es', 'Andrea', '695073140','14', 0', 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Jimenez', 'Soldado', '47546341N','frajimsol@alum.us.es', 'Franciso Javier','695073199', '16', 0, 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Montes', 'Saborido', '47546652J','eduardo@gmail.com', 'Eduardo', '695073369', '18', 0, 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Humanes', 'Garcia', '47546654Z','davidtenis1@gmail.com', 'David', '695074189', '20', 0, 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Bueno', 'Molina', '47546654D','rcbuenomolina@gmail.com', 'Rocio', '695014169', '22', 0, 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Guerrero', 'Fischer', '57526654L','paulaguerrerofischer@gmail.com', 'Paula', '695014129', '24', 0, 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Del Valle', 'Lopez de Santa Maria', '65526653W','javo13697@gmail.com', 'Javier', '652314129', '26', 0, 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Aguado', 'Delgado', '75572153B','saraaguadodelgado@gmail.com', 'Sara', '643135129', '29', 0, 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Bay', '', '74562153L','miriam.bay@gmail.com', 'Miriam', '643132547',  '31', 0, 0');
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Gonzales de la PeÃ±a', 'Garcia', '34562321P','marioogonzalez2@gmail.com', 'Mario', '643831247','33', 0, 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Jesus Sanchez', 'Cruz-Sagredo', '21562321U','juanje_chus@gmail.com', 'Juan', '678886247', '35', 0, 0);
INSERT INTO `profesores` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`,  `useraccount_id`,`tarifa_premium`,`expediente_validado`) VALUES ('Ruiz', 'Castilla', '12523211T','carmenrucasti@gmail.com', 'Carmen', '623816657', '37', 0, 0);



INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Prueba', 'Prueba', '47546221N', 'alumnoprueba@gmail.com', 'Prueba', '123456722', '2');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Rojas', 'Gutierrez', '47546231T', 'rodrojgut@alum.us.es', 'Rodrigo', '123456789', '3');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Romero', 'Caceres', '47546251Y', 'anaromcac@alum.us.es', 'Ana', '603552740', '4');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Squen', 'Saavedra', '47546223R', 'lizesqsaa@alum.us.es', 'Lizseth Katherine', '653552740', '9');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Lobato', 'Troncoso', '47546213E', 'josema_6497@alum.us.es', 'Jose Manuel', '603554740', '10');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Mauri', 'Mitchell', '47546233W', 'crimaumit@alum.us.es', 'Cristina Maria', '695073170', '11');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Meca', 'Sanchez', '47546343S', 'andmecsan@alum.us.es', 'Andrea', '695073140', '15');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Jimenez', 'Soldado', '47546341N', 'frajimsol@alum.us.es', 'Franciso Javier', '695073199', '17');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Montes', 'Saborido', '47546652J', 'eduardo@gmail.com', 'Eduardo', '695073369', '19');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Humanes', 'Garcia', '93546614Z', 'davidtenis1@gmail.com', 'David', '695074189', '21');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Bueno', 'Molina', '47546654D', 'rcbuenomolina@gmail.com', 'Rocio', '695014169', '23');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Guerrero', 'Fischer', '57526654L', 'paulaguerrerofischer@gmail.com', 'Paula', '695014129', '25');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Del Valle', 'Lopez de Santa Maria', '65526653W', 'javo13697@gmail.com', 'Javier', '652314129', '27');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Carillo', 'Mirando', '47546221Z', 'fcarmir@gmail.com', 'Fernando', '652314129', '28');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Aguado', 'Delgado', '75532153Q', 'saraaguadodelgado@gmail.com', 'Sara', '643135129', '30');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Bay', '', '74562153L', 'miriam.bay@gmail.com', 'Miriam', '643132547', '32');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Gonzales de la Peña', 'Garcia', '34562321P', 'marioogonzalez2@gmail.com', 'Mario', '643831247', '34');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Jesus Sanchez', 'Cruz-Sagredo', '21562321U', 'juanje_chus@gmail.com', 'Mario', '678886247', '36');
INSERT INTO `alumnos` (`apellido1`, `apellido2`, `dni`, `email`, `nombre`, `telefono`, `useraccount_id`) VALUES ('Ruiz', 'Castilla', '12523211T', 'carmenrucasti@gmail.com', 'Carmen', '623816657', '38');


INSERT INTO `foros` (`fecha_creacion`, `titulo`) VALUES ('2020-03-20', 'Foro Fundamentos de programación');
INSERT INTO `foros` (`fecha_creacion`, `titulo`) VALUES ('2020-03-20', 'Foro Análisis y Diseño de Datos y Algoritmos');
INSERT INTO `foros` (`fecha_creacion`, `titulo`) VALUES ('2020-03-20', 'Foro Diseño y pruebas 1');
INSERT INTO `foros` (`fecha_creacion`, `titulo`) VALUES ('2020-03-20', 'Foro Diseño y pruebas 2');
INSERT INTO `foros` (`fecha_creacion`, `titulo`) VALUES ('2020-03-20', 'Foro Complemento de Bases de Datos');


INSERT INTO `espacios` (`asignatura_id`, `foro_id`, `profesor_id`, `precio`) VALUES ('1', '1','1','8.0');
INSERT INTO `espacios` (`asignatura_id`, `foro_id`, `profesor_id`, `precio` ) VALUES ('2', '2','2','8.5');
INSERT INTO `espacios` (`asignatura_id`, `foro_id`, `profesor_id`, `precio`) VALUES ('3', '3','3','9.0');
INSERT INTO `espacios` (`asignatura_id`, `foro_id`, `profesor_id`, `precio`) VALUES ('4', '4','5','9.5');
INSERT INTO `espacios` (`asignatura_id`, `foro_id`, `profesor_id`, `precio`) VALUES ('5', '5','5','8');


INSERT INTO `horario` (`dia`,`fecha_inicio`,`fecha_fin`,`espacio_id`, `capacidad`) VALUES ('1','11:00:00','12:00:00','1',2);
INSERT INTO `horario` (`dia`,`fecha_inicio`,`fecha_fin`,`espacio_id`, `capacidad`) VALUES ('3','11:00:00','12:00:00','1',2);
INSERT INTO `horario` (`dia`,`fecha_inicio`,`fecha_fin`,`espacio_id`, `capacidad`)  VALUES ('3','18:30:00','19:30:00','1',6);
INSERT INTO `horario` (`dia`,`fecha_inicio`,`fecha_fin`,`espacio_id`, `capacidad`)  VALUES ('5','12:00:00','13:00:00','1',6);
INSERT INTO `horario` (`dia`,`fecha_inicio`,`fecha_fin`,`espacio_id`, `capacidad`)  VALUES ('2','20:00:00','21:00:00','2',12);
INSERT INTO `horario` (`dia`,`fecha_inicio`,`fecha_fin`,`espacio_id`, `capacidad`)  VALUES ('4','15:00:00','16:00:00','2',12);
INSERT INTO `horario` (`dia`,`fecha_inicio`,`fecha_fin`,`espacio_id`, `capacidad`)  VALUES ('4','15:00:00','16:00:00','5',4);



INSERT INTO `horario_alumnos` (`horario_id`, `alumnos_id`) VALUES ('1', '1');
INSERT INTO `horario_alumnos` (`horario_id`, `alumnos_id`) VALUES ('1', '2');
INSERT INTO `horario_alumnos` (`horario_id`, `alumnos_id`) VALUES ('3', '3');
INSERT INTO `horario_alumnos` (`horario_id`, `alumnos_id`) VALUES ('7', '3');
INSERT INTO `horario_alumnos` (`horario_id`, `alumnos_id`) VALUES ('7', '4');



INSERT INTO `respuestas` (`contenido`, `creacion_respuesta`, `user_account_id`, `foro_id`) VALUES ('Me falla al insertar en la base de datos la fecha....', '2020-03-20 11:00:00', '4', '5');
INSERT INTO `respuestas` (`contenido`, `creacion_respuesta`, `user_account_id`, `foro_id`) VALUES ('Hola, la manera en la que lo estas haciendo es la correcta, tengo una duda, como definiste tu columna FECHA en MYSQL, es una fecha? Saludos,', '2020-03-20 11:40:00', '7', '5');



