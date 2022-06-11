set foreign_key_checks = 0;

delete from cidade;
delete from estado;
delete from cliente;
delete from usuario;
delete from paciente;
delete from historico_peso;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table estado auto_increment = 1;
alter table cliente auto_increment = 1;
alter table usuario auto_increment = 1;
alter table paciente auto_increment = 1;
alter table historico_peso auto_increment = 1;


insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into cliente (id, nome, email, cpf, data_nascimento, sexo, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Jordana', 'jordana.ferreira@gmail.com', '341.660.341-97', utc_timestamp, 'FEMININO', 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into cliente (id, nome, email, cpf, data_nascimento, sexo, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (2, 'Carmem', 'carmemligia@gmail.com', '456.574.747-24', utc_timestamp, 'FEMININO', 2, '11692-999', 'Rua Eliseu Visconti', '60', 'Belas Artes');
insert into cliente (id, nome, email, cpf, data_nascimento, sexo, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (3, 'Alcione', 'alcione@gmail.com', '168.868.024-10', utc_timestamp, 'FEMININO', 3, '11740-000', 'Avenida Raul Cury', '1034', 'Cibratel');

insert into paciente (id, dono_id, nome, sexo, porte, data_nascimento, peso, temperatura, vivo, agressivo, reprodutivo) values (1, 1, 'Taz', 'MACHO', 'MEDIO', utc_timestamp, 7.200, 36.1, true, false, false);

insert into historico_peso(id, paciente_id, data_cadastro, peso) values (1, 1, utc_timestamp, 7.2);

insert into usuario (id, nome, email, senha, data_cadastro) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp);
