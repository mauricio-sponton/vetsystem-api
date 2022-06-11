package com.mj.vetsystem.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.mj.vetsystem.domain.event.PacienteSalvoEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Paciente extends AbstractAggregateRoot<Paciente>{

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
	
	private BigDecimal temperatura;
	
	private Boolean vivo = Boolean.TRUE;
	
	private Boolean agressivo = Boolean.FALSE;
	
	private Boolean reprodutivo = Boolean.TRUE;
	
	@ManyToOne
	private Cliente dono;
	
	public void registrarPeso() {
		registerEvent(new PacienteSalvoEvent(this));
	}
}
