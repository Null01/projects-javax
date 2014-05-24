DROP TABLE FUNCION_USUARIO;
DROP TABLE FUNCION;
DROP TABLE USUARIO;
DROP TABLE PERFIL;
DROP TABLE LOGIN;
DROP TABLE RECURSO;
DROP TABLE PRESTAMO;

CREATE TABLE FUNCION(
  id_funcion       serial not null,
  name_funcion     character varying(30) not null,
  url_funcion      character varying(50),
  id_funcion_padre integer not null,
  primary key(id_funcion)  
);

CREATE TABLE PERFIL(
  id_perfil     serial not null,
  name_perfil   character varying(30) not null,
  desc_perfil   character varying(256) not null,
  version      integer not null,
  primary key (id_perfil)
);

-- RELACION MANYTOMANY
CREATE TABLE FUNCION_USUARIO(
  id_perfil    integer not null,
  foreign key (id_perfil) references PERFIL(id_perfil), 
  id_funcion   integer not null,
  foreign key (id_funcion) references FUNCION(id_funcion),
  primary key (id_perfil,id_funcion)
);


INSERT INTO funcion VALUES (1,'Administracion perfiles','/adminPerfiles/adminPerfil.xhtml',1);
INSERT INTO funcion VALUES (2,'Administracion recursos','/adminRecursos/adminRecurso.xhtml',2);
INSERT INTO funcion VALUES (3,'Administracion Prestamo','/adminPrestamos/adminPrestamo.xhtml',3);
INSERT INTO funcion VALUES (4,'Administracion DB', null , 4);
INSERT INTO funcion VALUES (5,'Ejecutar comandos SQL','/adminDB/adminDB.xhtml',4);
INSERT INTO funcion VALUES (6,'Prestamo recursos','/adminPrestamos/prestamoUsuario.xhtml',6);
--INSERT INTO funcion VALUES (7,'Administracion usuarios','/adminUsuario/adminUsuario.xhtml',7);

INSERT INTO perfil VALUES (1,'ADMINISTRADOR','PERFIL QUE POSEE TODOS LOS PERMISOS DE LA APLICACION.',0);
INSERT INTO perfil VALUES (2,'CLIENTE','PERFIL USUARIO FINAL.',0);

INSERT INTO funcion_usuario VALUES (1,1);
INSERT INTO funcion_usuario VALUES (1,2);
INSERT INTO funcion_usuario VALUES (1,3);
INSERT INTO funcion_usuario VALUES (1,4);
INSERT INTO funcion_usuario VALUES (1,5);
INSERT INTO funcion_usuario VALUES (2,6);
INSERT INTO funcion_usuario VALUES (1,7);

 CREATE TABLE LOGIN(
  name_user     character varying(60) not null,
  pass_user     character varying(60) not null,
  count_trys    smallint not null,
  date_last_try date,
  primary key(name_user,pass_user)
);

CREATE TABLE USUARIO(
  id_perfil     serial not null,
  foreign key (id_perfil)  references PERFIL(id_perfil),
  name_user     character varying(60) not null,
  primary key(name_user),
  pass_user     character varying(60) not null,
  foreign key (name_user,pass_user)  references LOGIN(name_user,pass_user),  
  nombre        character varying(30) not null,
  apellido      character varying(30) not null,
  edad          date not null,
  correo        character varying(40) not null,
  version      integer not null
);

INSERT INTO login VALUES ('usuario','7ce5fc91006811437bb1496e1e1afaf4',0,null);
INSERT INTO login VALUES ('admin','77e2edcc9b40441200e31dc57dbb8829',0,null);

INSERT INTO usuario VALUES (2,'usuario','7ce5fc91006811437bb1496e1e1afaf4','Pedro','Pedraza','2000-01-01','pedropedraza@correo.com',0);
INSERT INTO usuario VALUES (1,'admin','77e2edcc9b40441200e31dc57dbb8829','Admin','Super','1992-03-26','superadmin@correo.com',0);

CREATE TABLE RECURSO(
  id_recurso        serial not null,
  codigo_barras     numeric(20) not null,
  nombre            character varying(30),
  descripcion       character varying(256),
  version           integer not null,
  primary key(id_recurso)
);


INSERT INTO recurso VALUES (DEFAULT,123456,'CAMARA','DESCRIPCION CAMARA',0);
INSERT INTO recurso VALUES (DEFAULT,123457,'COMPUTADORES','DESCRIPCION COMPURADORES',0);
INSERT INTO recurso VALUES (DEFAULT,123458,'PARLANTES','DESCRIPCION PARLANTES',0);
INSERT INTO recurso VALUES (DEFAULT,123459,'VIDEOBEAM','DESCRIPCION VIDEOBEAM',0);
INSERT INTO recurso VALUES (DEFAULT,123450,'SALAS AUDIVISUALES','DESCRIPCION AUDIOVISUALES',0);

CREATE TABLE PRESTAMO(
  id_recurso        integer not null,
  id_usuario        character varying(30) not null,
  fecha_prestamo    timestamp not null,  
  fecha_devolucion  timestamp,
  fecha_solicitud   timestamp not null,
  version           integer not null,
  primary key (id_usuario,id_recurso,fecha_prestamo)
);


--INSERT INTO prestamo VALUES (3,'usuario','2014-05-20','14:00:00','15:00:00',0);
--INSERT INTO prestamo VALUES (1,'usuario','2014-05-22','12:00:00',null,0);

DROP TABLE AUDITOR;

create table AUDITOR(
	idAuditoria serial,
	nameClass character varying(100) not null,
	nameMethod character varying(100) not null,
	named character varying(100) not null,
	primary key(idAuditoria)
)