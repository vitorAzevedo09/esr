create table restaurante (
  id bigint not null auto_increment,
  nome varchar(255) not null,
  taxa_frete decimal(19,2) not null,
  cozinha_id bigint not null,
  endereco_cidade_id bigint not null,
  endereco_cep varchar(11) not null,
  endereco_complemento varchar(255),
  endereco_logradouro varchar(255),
  endereco_numero varchar(11) not null,
  endereco_bairro varchar(255) not null,
  primary key (id)
) engine=InnoDB;

alter table restaurante 
  add constraint FK76grk4roudh659skcgbnanthi 
  foreign key (cozinha_id) references cozinha (id);
