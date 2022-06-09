package com.mj.vetsystem.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PacienteModel {

    private Long id;
    private String nome;
    private String sexo;
    private String porte;
    private OffsetDateTime dataNascimento;
    private BigDecimal peso;
    
}