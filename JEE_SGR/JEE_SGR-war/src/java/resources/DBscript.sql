
DROP TABLE funcion CASCADE;
DROP TABLE funcion_usuario CASCADE;
DROP TABLE PERFIL CASCADE;

CREATE TABLE FUNCION(
  id_funcion     serial not null,
  name_funcion   character varying(30) not null,
  url_funcion    character varying(50) not null,
  id_funcion_padre serial not null,
  primary key(id_funcion)  
);

CREATE TABLE PERFIL(
  id_perfil     serial not null,
  name_perfil   character varying(30) not null,
  primary key (id_perfil)
);

-- RELACION MANYTOMANY
CREATE TABLE FUNCION_USUARIO(
  id_perfil    serial not null,
  foreign key (id_perfil) references PERFIL(id_perfil), 
  id_funcion   serial not null,
  foreign key (id_funcion) references FUNCION(id_funcion),
  primary key (id_perfil,id_funcion)
);


INSERT INTO funcion VALUES (1,'Admin perfiles','/adminPerfiles/adminPerfiles.xhtml',1);
INSERT INTO funcion VALUES (2,'Admin prestamos','/adminPrestamo/adminPrestamo.xhtml',1);

INSERT INTO perfil VALUES (1,'ADMINISTRADOR');
INSERT INTO perfil VALUES (2,'CLIENTE');

INSERT INTO funcion_usuario VALUES (1,1);
INSERT INTO funcion_usuario VALUES (1,2);
INSERT INTO funcion_usuario VALUES (2,1);


DROP TABLE login CASCADE;
DROP TABLE usuario CASCADE;

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

INSERT INTO usuario VALUES (2,'usuario','pedro','pedraza','01-01-2000','pedropedraza@correo.com');
INSERT INTO usuario VALUES (1,'admin','admin','super','26-03-1992','superadmin@correo.com');

INSERT INTO login VALUES ('usuario','usuario');
INSERT INTO login VALUES ('admin','admin');



DROP TABLE recurso CASCADE;
DROP TABLE articulo CASCADE;

CREATE TABLE RECURSO(
  id_recurso     serial not null,
  nombre         character varying(30),
  descripcion    character varying(80),
  primary key(id_recurso)
);

-- RELACION MANYTOONE
CREATE TABLE ARTICULO(
  id_articulo    serial not null,
  id_recurso     serial not null,
  marca          character varying(30),
  disponible     boolean,  
  primary key (id_articulo, id_recurso),
  foreign key (id_recurso) references RECURSO(id_recurso)  
);


INSERT INTO recurso VALUES (1,'CAMARA','');
INSERT INTO recurso VALUES (2,'COMPUTADORES','');
INSERT INTO recurso VALUES (3,'PARLANTES','');
INSERT INTO recurso VALUES (4,'VIDEOBEAM','');
INSERT INTO recurso VALUES (5,'SALAS AUDIVISUALES','');
INSERT INTO recurso VALUES (6,'OTROS','');


INSERT INTO articulo VALUES (1,1,'SONY',true);
INSERT INTO articulo VALUES (2,1,'VIO',true);
INSERT INTO articulo VALUES (1,2,'LENOVO',true);
INSERT INTO articulo VALUES (2,2,'TOSHIBA',true);
INSERT INTO articulo VALUES (3,2,'COMPAQ',true);
INSERT INTO articulo VALUES (4,2,'MAC',true);

DROP TABLE prestamo CASCADE;

CREATE TABLE PRESTAMO(
  id_recurso     serial not null,
  id_articulo    serial not null,
  id_usuario     character varying(30) not null,
  fecha_prestamo date,
  fecha_entrega  date,
  primary key (id_usuario,id_recurso,id_articulo,fecha_prestamo)
);

INSERT INTO prestamo VALUES (1,2,'usuario','28-02-2014','02-03-2014');
INSERT INTO prestamo VALUES (2,2,'usuario','28-02-2014','02-03-2014');
INSERT INTO prestamo VALUES (2,3,'usuario','28-02-2014','02-03-2014');

