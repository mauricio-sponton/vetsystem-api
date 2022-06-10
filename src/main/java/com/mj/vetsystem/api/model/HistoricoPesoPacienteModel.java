package com.mj.vetsystem.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HistoricoPesoPacienteModel {

    private Long id;
    private BigDecimal peso;
    private OffsetDateTime dataCadastro;
    
}