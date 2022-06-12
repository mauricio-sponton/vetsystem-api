package com.mj.vetsystem.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RacaInput {

    @NotBlank
    private String nome;
    
    @Valid
    @NotNull
    private EspecieIdInput especie;
    
}