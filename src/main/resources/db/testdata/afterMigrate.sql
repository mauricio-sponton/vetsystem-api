set foreign_key_checks = 0;

delete from cidade;
delete from estado;
delete from cliente;
delete from usuario;
delete from paciente;
delete from historico_peso;
delete from especie;
delete from raca;
delete from internacao;
delete from progresso_tratamento;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table estado auto_increment = 1;
alter table cliente auto_increment = 1;
alter table usuario auto_increment = 1;
alter table paciente auto_increment = 1;
alter table historico_peso auto_increment = 1;
alter table especie auto_increment = 1;
alter table raca auto_increment = 1;
alter table internacao auto_increment = 1;
alter table progresso_tratamento auto_increment = 1;


insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into cliente (id, nome, email, cpf, data_nascimento, sexo, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Jordana', 'jordana.ferreira@gmail.com', '341.660.341-97', '2019-11-01 11:14:04', 'FEMININO', 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into cliente (id, nome, email, cpf, data_nascimento, sexo, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (2, 'Carmem', 'carmemligia@gmail.com', '456.574.747-24', '2019-11-01 11:14:04', 'FEMININO', 2, '11692-999', 'Rua Eliseu Visconti', '60', 'Belas Artes');
insert into cliente (id, nome, email, cpf, data_nascimento, sexo, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (3, 'Alcione', 'alcione@gmail.com', '168.868.024-10', '2019-11-01 11:14:04', 'FEMININO', 3, '11740-000', 'Avenida Raul Cury', '1034', 'Cibratel');



insert into especie (id, nome) values (1, 'Canino');
insert into especie (id, nome) values (2, 'Felino');

insert into raca (id, especie_id, nome) values (1, 1, 'Bulldog');
insert into raca (id, especie_id, nome) values (2, 1, 'Poodle');
insert into raca (id, especie_id, nome) values (3, 1, 'Husky');
insert into raca (id, especie_id, nome) values (4, 2, 'Persa');
insert into raca (id, especie_id, nome) values (5, 2, 'Siamês');

insert into paciente (id, dono_id, raca_id, nome, sexo, porte, data_nascimento, peso, temperatura, vivo, agressivo, reprodutivo) values (1, 1, 2, 'Taz', 'MACHO', 'MEDIO', '2019-11-01 11:14:04', 7.200, 36.1, true, false, false);
insert into paciente (id, dono_id, raca_id, nome, sexo, porte, data_nascimento, peso, temperatura, vivo, agressivo, reprodutivo) values (2, 1, 4, 'Loki', 'MACHO', 'PEQUENO', '2019-11-01 11:14:04', 1.600, 38.1, true, true, false);

insert into historico_peso (id, paciente_id, data_cadastro, peso) values (1, 1, utc_timestamp, 7.2);

insert into internacao (id, paciente_id, data_admissao, data_alta, peso, temperatura, status, prognostico, diagnostico, observacoes) values (1, 1, '2022-06-01 11:34:04', null, 33, 38.5, 'ATIVA', 'Paciente vomitando.', '', '');
insert into internacao (id, paciente_id, data_admissao, data_alta, peso, temperatura, status, prognostico, diagnostico, observacoes) values (2, 2, '2019-11-01 11:14:04', null, 33, 38.5, 'TERMINADA', 'Manchas na pele.', 'Sarna', '');

insert into progresso_tratamento (id, internacao_id, nome, data, dose, via_medicamento) values (1, 2, 'Aplicação de pomada para sarna', '2019-11-01 11:34:04', null, 'CUTANEA');
insert into progresso_tratamento (id, internacao_id, nome, data, dose, via_medicamento) values (2, 2, 'Calmante', '2019-11-02 20:34:04', null, 'ORAL');

insert into usuario (id, nome, email, senha, data_cadastro) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp);
