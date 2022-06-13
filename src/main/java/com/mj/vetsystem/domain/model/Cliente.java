package com.mj.vetsystem.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String cpf;
	
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataNascimento;
	
	@Enumerated(EnumType.STRING)
	private SexoCliente sexo;
	
	@Embedded
	private Endereco endereco;
}
