package com.mj.vetsystem.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Paciente {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private SexoPaciente sexo;
	
	@Enumerated(EnumType.STRING)
	private PortePaciente porte;
	
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataNascimento;
	
	private BigDecimal peso;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private List<HistoricoPeso> historicoPeso;
	
	
}
