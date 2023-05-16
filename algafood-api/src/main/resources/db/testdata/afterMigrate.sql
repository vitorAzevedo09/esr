SET FOREIGN_KEY_CHECKS=0;

delete from estado;
delete from cidade;
delete from cozinha;
delete from restaurante;



insert ignore into cozinha (id, nome) values (1, 'Tailandesa');
insert ignore into cozinha (id, nome) values (2, 'Indiana');

insert ignore into restaurante (id, nome, taxa_frete, cozinha_id, ativo)
  values (1, 'Thai Gourmet', 10, 1, 1);
insert ignore into restaurante (id, nome, taxa_frete, cozinha_id, ativo) 
  values (2, 'Thai Delivery', 9.15, 1, 1);
insert ignore into restaurante (id, nome, taxa_frete, cozinha_id, ativo) 
  values (3, 'Tuk Tuk Comida', 15.73, 2, 1);

  
insert ignore into estado (id, nome) values (1, 'Piaui');
insert ignore into estado (id, nome) values (2, 'Maranhao');


insert ignore into cidade (id, nome, estado_id) values (1, 'Parnaiba', 1);
insert ignore into cidade (id, nome, estado_id) values (2, 'Araioses', 2);
