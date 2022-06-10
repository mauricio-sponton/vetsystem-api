package com.mj.vetsystem.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.mj.vetsystem.domain.model.PortePaciente;
import com.mj.vetsystem.domain.model.SexoPaciente;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PacienteModel {

    private Long id;
    private String nome;
    private SexoPaciente sexo;
    private PortePaciente porte;
    private OffsetDateTime dataNascimento;
    private BigDecimal peso;
    private BigDecimal temperatura;
    private Boolean vivo;
    private Boolean agressivo;
    private Boolean reprodutivo;
    
    public String getSexo() {
    	return sexo.getDescricao();
    }
    
    public String getPorte() {
    	return porte.getDescricao();
    }
    
}