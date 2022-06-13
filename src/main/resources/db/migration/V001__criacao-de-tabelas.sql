create table estado (
	id bigint not null auto_increment, 
    nome varchar(80) not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table cidade (
	id bigint not null auto_increment,
	estado_id bigint not null,
	nome varchar(80) not null,    
	primary key (id),
	constraint fk_cidade_estado foreign key (estado_id) references estado (id)
) engine=InnoDB default charset=utf8;

create table usuario (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	email varchar(255) not null,
	senha varchar(255) not null,
	data_cadastro datetime not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table paciente (
	id bigint not null auto_increment,
	dono_id bigint not null,
	raca_id bigint not null,
	nome varchar(80) not null,
	sexo varchar(20),
	porte varchar(20),
	data_nascimento datetime not null,
	peso decimal(6,3),
	temperatura decimal(3,1),
	vivo tinyint(1),
	agressivo tinyint(1),
	reprodutivo tinyint(1),

	primary key (id)

) engine=InnoDB default charset=utf8;

create table historico_peso (
	id bigint not null auto_increment,
	paciente_id bigint not null,
	data_cadastro datetime not null,
	peso decimal(6,3),

	primary key (id)

) engine=InnoDB default charset=utf8;


create table especie (
	id bigint not null auto_increment, 
    nome varchar(80) unique not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table raca (
	id bigint not null auto_increment,
	especie_id bigint not null,
	nome varchar(80) not null,    
	primary key (id)
) engine=InnoDB default charset=utf8;


create table cliente (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	email varchar(60) unique not null,
	cpf varchar(14) unique not null,
	data_nascimento datetime not null,
    sexo varchar(20),
    
	endereco_cidade_id bigint,
	endereco_cep varchar(9),
	endereco_logradouro varchar(100),
	endereco_numero varchar(20),
	endereco_complemento varchar(60),
	endereco_bairro varchar(60),
	
	primary key (id)
) engine=InnoDB default charset=utf8;

alter table cliente add constraint fk_cliente_cidade
foreign key (endereco_cidade_id) references cidade (id);

alter table paciente add constraint fk_paciente_dono
foreign key (dono_id) references cliente (id);

alter table paciente add constraint fk_paciente_raca
foreign key (raca_id) references raca (id);

alter table raca add constraint fk_raca_especie
foreign key (especie_id) references especie (id);

alter table historico_peso add constraint fk_historico_peso_paciente
foreign key (paciente_id) references paciente (id);