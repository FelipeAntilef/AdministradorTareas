create table tb_tareas(
id bigint not null auto_increment,
Titulo varchar(250),
descripcion varchar (500),
fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
estado varchar(100),
creador varchar(100),
primary key (id)


)