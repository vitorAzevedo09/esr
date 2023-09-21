create table usuario (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	email varchar(255) not null,
	senha varchar(255) not null,
	data_cadastro datetime not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table usuario_grupo (
	usuario_id bigint not null,
	grupo_id bigint not null,
	
	primary key (usuario_id, grupo_id)
) engine=InnoDB default charset=utf8;


alter table usuario_grupo add constraint fk_usuario_grupo_grupo
foreign key (grupo_id) references grupo (id);

alter table usuario_grupo add constraint fk_usuario_grupo_usuario
foreign key (usuario_id) references usuario (id);
