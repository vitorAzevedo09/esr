create table restaurante (
  id bigint not null auto_increment,
  nome varchar(255) not null,
  taxa_frete decimal(19,2) not null,
  cozinha_id bigint not null,
  primary key (id)
) engine=InnoDB;

alter table restaurante 
  add constraint FK76grk4roudh659skcgbnanthi 
  foreign key (cozinha_id) references cozinha (id);
