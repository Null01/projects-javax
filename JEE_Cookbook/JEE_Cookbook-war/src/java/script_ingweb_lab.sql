drop table commentsPublish;
drop table publish;
drop table usuario;
drop table login;

CREATE TABLE usuario
(
  email character varying(400) not null,  
  fname character varying(400) not null,
  lname character varying(400) not null,
  CONSTRAINT user_pkey PRIMARY KEY (email)
);

create table login(
  email character varying(400) not null,
  pass  character varying(400) not null,
  typeUser character varying(30) not null,
  CONSTRAINT login_pkey PRIMARY KEY (email,pass),
  constraint type_check check(typeUser = 'ADMIN' or typeUser = 'SIMPLE')
);

create table publish(
  idpublish serial not null,
  email character varying(400) not null,  
  tittle character varying(400) not null,
  description character varying(2000) not null,
  datecreated date not null,
  CONSTRAINT publish_pkey PRIMARY KEY (idpublish),
  CONSTRAINT usuario_fkey FOREIGN KEY (email) REFERENCES usuario(email)
);

create table commentsPublish(
  idcomment serial not null,
  email character varying(400) not null,
  idpublish serial not null,
  idcommentToOtherComment serial,
  commentsPublish character varying(2000) not null,
  datecreated date not null,
  CONSTRAINT commentsPublish_pkey PRIMARY KEY (idcomment),
  CONSTRAINT usuario_fkey FOREIGN KEY (email) REFERENCES usuario(email),
  CONSTRAINT publish_fkey FOREIGN KEY (idpublish) REFERENCES publish(idpublish)
);



insert into login values ('andresduran0502@gmail.com','5a22e6c339c96c9c0513a46e44c39683', 'ADMIN');
insert into usuario values ('andresduran0502@gmail.com', 'Andres Felipe','Garcia Duran' );

insert into login values ('estebancastiblanco02@gmail.com','5a22e6c339c96c9c0513a46e44c39683', 'ADMIN');
insert into usuario values ('estebancastiblanco02@gmail.com', 'Esteban','Castiblanco Moncaleano' );

insert into login values ('agarciad1@ucentral.edu.co','411cd305d84659e7479e5700063be2bb','SIMPLE');
insert into usuario values ('agarciad1@ucentral.edu.co', 'Andres','Duran' );
insert into publish values(default, 'agarciad1@ucentral.edu.co', 'Primera publicacion','Aqui va la descripcion','2015-03-21');

insert into commentsPublish values (default, 'agarciad1@ucentral.edu.co', 1, 0,'Aqui va el comentario1','2015-03-21');
insert into commentsPublish values (default, 'agarciad1@ucentral.edu.co', 1, 0,'Aqui va el comentario2','2015-03-21');
insert into commentsPublish values (default, 'agarciad1@ucentral.edu.co', 1, 2, 'Aqui va el comentario3','2015-03-21');


