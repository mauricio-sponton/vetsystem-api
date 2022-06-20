package com.mj.vetsystem.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mj.vetsystem.domain.model.enums.StatusInternacao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InternacaoModel {

    private Long id;
    private PacienteInternacaoModel paciente;
    
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime dataAdmissao;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime dataAlta;
    
    private BigDecimal peso;
    private BigDecimal temperatura;
    
    private StatusInternacao status;
    
    private String prognostico;
    private String diagnostico;
    private String observacoes;
    
    private List<ProgressoTratamentoModel> tratamentos;
   
    
   
    
}