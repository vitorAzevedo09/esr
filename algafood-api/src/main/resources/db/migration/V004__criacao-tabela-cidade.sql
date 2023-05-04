create table cidade (
  id bigint not null auto_increment,
  nome varchar(255),
  estado_id bigint,
  primary key (id)
) engine=InnoDB;

alter table cidade 
  add constraint FKkworrwk40xj58kevvh3evi500 
  foreign key (estado_id) references estado (id);
