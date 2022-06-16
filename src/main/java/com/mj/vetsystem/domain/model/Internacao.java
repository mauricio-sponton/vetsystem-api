package com.mj.vetsystem.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mj.vetsystem.domain.model.enums.StatusInternacao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Internacao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal peso;
	
	private BigDecimal temperatura;
	
	@Enumerated(EnumType.STRING)
	private StatusInternacao status = StatusInternacao.ATIVA;
	
	private OffsetDateTime dataAdmissao;
	private OffsetDateTime dataTermino;
	
	@Lob
	private String prognostico;
	
	@Lob
	private String diagnostico;
	
	@Lob
	private String observacoes;
	
	@ManyToOne
	private Paciente paciente;
	
	@OneToMany(mappedBy = "internacao", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TratamentoInternacao> tratamentos = new ArrayList<>();
}
