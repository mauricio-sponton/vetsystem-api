package com.mj.vetsystem.api.model.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.mj.vetsystem.domain.model.PortePaciente;
import com.mj.vetsystem.domain.model.SexoPaciente;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PacienteInput {

    @NotBlank
    private String nome;
    
    @NotNull
    private SexoPaciente sexo;
    
    @NotNull
    private PortePaciente porte;
    
    @NotNull
    private OffsetDateTime dataNascimento;
    
    @Positive
    private BigDecimal peso;
    
}