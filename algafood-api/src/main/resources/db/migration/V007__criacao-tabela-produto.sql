create table produto (
	id bigint not null auto_increment,
	restaurante_id bigint not null,
	nome varchar(80) not null,
	descricao text not null,
	preco decimal(10,2) not null,
	ativo tinyint(1) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;

alter table produto add constraint fk_produto_restaurante
foreign key (restaurante_id) references restaurante (id);

