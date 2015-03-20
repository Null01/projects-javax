drop table usuario;
CREATE TABLE usuario
(
  email character varying(400) not null,  
  fname character varying(400) not null,
  lname character varying(400) not null,
  CONSTRAINT user_pkey PRIMARY KEY (email)
);

drop table login;
create table login(
  email character varying(400) not null,
  pass  character varying(400) not null,
  typeUser character varying(30) not null,
  CONSTRAINT login_pkey PRIMARY KEY (email,pass),
  constraint type_check check(typeUser = 'ADMIN' or typeUser = 'SIMPLE')
);

insert into login values ('andresduran0502@gmail.com','5a22e6c339c96c9c0513a46e44c39683', 'ADMIN');
insert into usuario values ('andresduran0502@gmail.com', 'Andres Felipe','Garcia Duran' );