
insert ignore into cozinha (nome) values ('Tailandesa');
insert ignore into cozinha (nome) values ('Indiana');

insert ignore into restaurante (nome, taxa_frete, cozinha_id)
  values ('Thai Gourmet', 10, 1);
insert ignore into restaurante (nome, taxa_frete, cozinha_id) 
  values ('Thai Delivery', 9.15, 1);
insert ignore into restaurante (nome, taxa_frete, cozinha_id) 
  values ('Tuk Tuk Comida', 15.73, 2);

  
insert ignore into estado (nome) values ('Piaui');
insert ignore into estado (nome) values ('Maranhao');


insert ignore into cidade (nome) values ('Parnaiba');
insert ignore into cidade (nome) values ('Araioses');
