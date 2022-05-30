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