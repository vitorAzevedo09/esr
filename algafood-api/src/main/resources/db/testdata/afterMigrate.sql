SET FOREIGN_KEY_CHECKS=0;

delete from estado;
delete from cidade;
delete from cozinha;
delete from restaurante;



insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, ativo) values (1, 'Thai Gourmet', 10, 1, 3,'38400-999', 'Rua João Pinheiro', '1000', 'Centro', 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, ativo) values (2, 'Thai Delivery', 9.50, 1, 1, '64218-150', 'Rua Carvalho Gonçalves', '2003', 'São Francisco', 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro, ativo) values (3, 'Tuk Tuk Comida Indiana', 15, 2, 1, '64218-100', 'Rua das Dores', '2837', 'Do Carmo', 1);
