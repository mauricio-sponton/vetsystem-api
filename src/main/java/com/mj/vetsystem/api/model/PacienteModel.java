package com.mj.vetsystem.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mj.vetsystem.domain.model.enums.PortePaciente;
import com.mj.vetsystem.domain.model.enums.SexoPaciente;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PacienteModel {

    private Long id;
    private String nome;
    private SexoPaciente sexo;
    private PortePaciente porte;
    
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime dataNascimento;
    private BigDecimal peso;
    private BigDecimal temperatura;
    private Boolean vivo;
    private Boolean agressivo;
    private Boolean reprodutivo;
    private ClienteModel dono;
    private RacaModel raca;
    
    public String getSexo() {
    	return sexo.getDescricao();
    }
    
    public String getPorte() {
    	return porte.getDescricao();
    }
    
}