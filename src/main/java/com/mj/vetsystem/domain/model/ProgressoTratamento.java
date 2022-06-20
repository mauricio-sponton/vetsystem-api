package com.mj.vetsystem.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.mj.vetsystem.domain.model.enums.ViaMedicamento;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ProgressoTratamento {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	private String nome;
	
	private OffsetDateTime data;
	
	private Integer dose;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "via_medicamento")
	private ViaMedicamento viaMedicamento;
	
	@ManyToOne
	private Internacao internacao;

	
}
