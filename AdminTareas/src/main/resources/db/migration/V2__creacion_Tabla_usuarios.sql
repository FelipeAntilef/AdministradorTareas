create table tb_Usuario(
id bigint not null auto_increment,
DNI varchar(15)unique not null,
Nombre varchar(250) not null,
Email varchar(250)unique not null,
usuario varchar(250)unique not null,
primary key(id)
)
