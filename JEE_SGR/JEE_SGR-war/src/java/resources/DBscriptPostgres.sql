DROP TABLE FUNCION_USUARIO;
DROP TABLE FUNCION;
DROP TABLE USUARIO;
DROP TABLE PERFIL;
DROP TABLE LOGIN;
DROP TABLE ARTICULO;
DROP TABLE RECURSO;
DROP TABLE PRESTAMO;

CREATE TABLE FUNCION(
  id_funcion       serial not null,
  name_funcion     character varying(30) not null,
  url_funcion      character varying(50) not null,
  id_funcion_padre integer not null,
  primary key(id_funcion)  
);

CREATE TABLE PERFIL(
  id_perfil     serial not null,
  name_perfil   character varying(30) not null,
  desc_perfil   character varying(256) not null,
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

INSERT INTO perfil VALUES (1,'ADMINISTRADOR','PERFIL QUE POSEE TODOS LOS PERMISOS DE LA APLICACION.');
INSERT INTO perfil VALUES (2,'CLIENTE','PERFIL USUARIO FINAL.');

INSERT INTO funcion_usuario VALUES (1,1);
INSERT INTO funcion_usuario VALUES (1,2);
INSERT INTO funcion_usuario VALUES (2,1);
INSERT INTO funcion_usuario VALUES (1,3);


CREATE TABLE USUARIO(
  id_perfil     serial not null,
  foreign key (id_perfil)  references PERFIL(id_perfil),
  name_user     character varying(30) not null,
  nombre        character varying(30) not null,
  apellido      character varying(30) not null,
  edad          date not null,
  correo        character varying(40) not null,
  primary key(name_user)
);

 CREATE TABLE LOGIN(
  name_user character varying(30) not null,
  pass_user character varying(30) not null,
  primary key(name_user,pass_user)
);

INSERT INTO usuario VALUES (2,'usuario2','Carlos','Jimenez','2000-01-01','pedropedraza@correo.com');
INSERT INTO usuario VALUES (2,'usuario','Pedro','Pedraza','2000-01-01','pedropedraza@correo.com');
INSERT INTO usuario VALUES (1,'admin','Admin','Super','1992-03-26','superadmin@correo.com');

INSERT INTO login VALUES ('usuario2','usuario2');
INSERT INTO login VALUES ('usuario','usuario');
INSERT INTO login VALUES ('admin','admin');

CREATE TABLE RECURSO(
  id_recurso     serial not null,
  nombre         character varying(30),
  descripcion    character varying(256),
  primary key(id_recurso)
);

-- RELACION MANYTOONE
CREATE TABLE ARTICULO(
  id_articulo    serial not null,
  id_recurso     integer not null,
  marca          character varying(30),
  disponible     smallint,  
  notes          character varying(256),
  primary key (id_articulo, id_recurso),
  foreign key (id_recurso) references RECURSO(id_recurso)  
);


INSERT INTO recurso VALUES (DEFAULT,'CAMARA','DESCRIPCION CAMARA');
INSERT INTO recurso VALUES (DEFAULT,'COMPUTADORES','DESCRIPCION COMPURADORES');
INSERT INTO recurso VALUES (DEFAULT,'PARLANTES','DESCRIPCION PARLANTES');
INSERT INTO recurso VALUES (DEFAULT,'VIDEOBEAM','DESCRIPCION VIDEOBEAM');
INSERT INTO recurso VALUES (DEFAULT,'SALAS AUDIVISUALES','DESCRIPCION AUDIOVISUALES');
INSERT INTO recurso VALUES (DEFAULT,'OTROS','');


INSERT INTO articulo VALUES (DEFAULT,1,'SONY',1,'');
INSERT INTO articulo VALUES (DEFAULT,1,'VIO',1,'');
INSERT INTO articulo VALUES (DEFAULT,2,'LENOVO',1,'');
INSERT INTO articulo VALUES (DEFAULT,2,'TOSHIBA',1,'');
INSERT INTO articulo VALUES (DEFAULT,2,'COMPAQ',1,'');
INSERT INTO articulo VALUES (DEFAULT,2,'MAC',1,'');

CREATE TABLE PRESTAMO(
  id_recurso     integer not null,
  id_articulo    integer not null,
  id_usuario     character varying(30) not null,
  fecha_prestamo date,
  fecha_entrega  date,
  primary key (id_usuario,id_recurso,id_articulo,fecha_prestamo)
);

INSERT INTO prestamo VALUES (1,2,'usuario','2014-02-28','2014-02-03');
INSERT INTO prestamo VALUES (2,2,'usuario','2014-02-28','2014-02-03');
INSERT INTO prestamo VALUES (2,3,'usuario','2014-02-28','2014-02-03');
