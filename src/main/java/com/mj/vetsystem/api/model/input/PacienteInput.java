package com.mj.vetsystem.api.model.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;

import com.mj.vetsystem.domain.model.PortePaciente;
import com.mj.vetsystem.domain.model.SexoPaciente;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PacienteInput {

    @NotBlank
    private String nome;
    
    private SexoPaciente sexo;
    private PortePaciente porte;
    private OffsetDateTime dataNascimento;
    private BigDecimal peso;
    
}