drop table commentsPublish;
drop table publish;
drop table login;
drop table usuario;


CREATE TABLE usuario(
  email character varying(400) not null,  
  fname character varying(400) not null,
  lname character varying(400) not null,
  constraint usuario_pkey primary key (email)
);

create table login(
  email character varying(400) not null,
  pass  character varying(400) not null,
  typeUser character varying(30) not null,
  dateCreated timestamp not null,
  constraint login_pkey primary key (email),
  constraint login_fkey foreign key (email) references usuario(email),
  constraint type_check check(typeUser = 'ADMIN' or typeUser = 'SIMPLE')
);

create table publish(
  idpublish serial not null,
  email character varying(400) not null,  
  tittle character varying(400) not null,
  description character varying(2000) not null,
  datecreated timestamp not null,
  CONSTRAINT publish_pkey PRIMARY KEY (idpublish),
  CONSTRAINT usuario_fkey FOREIGN KEY (email) REFERENCES usuario(email)
);

create table commentsPublish(
  idcomment serial not null,
  email character varying(400) not null,
  idpublish serial not null,
  idcommentToOtherComment serial,
  commentsPublish character varying(2000) not null,
  datecreated timestamp not null,
  CONSTRAINT commentsPublish_pkey PRIMARY KEY (idcomment),
  CONSTRAINT usuario_fkey FOREIGN KEY (email) REFERENCES usuario(email),
  CONSTRAINT publish_fkey FOREIGN KEY (idpublish) REFERENCES publish(idpublish)
);

insert into usuario values ('andresduran0502@gmail.com', 'Andres Felipe','Garcia Duran');
insert into login values ('andresduran0502@gmail.com','5a22e6c339c96c9c0513a46e44c39683', 'ADMIN', current_timestamp);

insert into usuario values ('estebancastiblanco02@gmail.com', 'Esteban','Castiblanco Moncaleano');
insert into login values ('estebancastiblanco02@gmail.com','5a22e6c339c96c9c0513a46e44c39683', 'ADMIN', current_timestamp);

insert into usuario values ('dpinedar@ucentral.edu.co', 'Diego Nicolas','Pineda');
insert into login values ('dpinedar@ucentral.edu.co','411cd305d84659e7479e5700063be2bb','SIMPLE', current_timestamp);

insert into usuario values ('agarciad1@ucentral.edu.co', 'Andres','Duran');
insert into login values ('agarciad1@ucentral.edu.co','411cd305d84659e7479e5700063be2bb','SIMPLE', current_timestamp);
insert into publish values(default, 'agarciad1@ucentral.edu.co', 'Primera publicacion','Aqui va la descripcion','2015-03-21');

insert into commentsPublish values (default, 'dpinedar@ucentral.edu.co', 1, 0,'Aqui va el comentario1','2015-03-21');
insert into commentsPublish values (default, 'dpinedar@ucentral.edu.co', 1, 0,'Aqui va el comentario2','2015-03-21');
