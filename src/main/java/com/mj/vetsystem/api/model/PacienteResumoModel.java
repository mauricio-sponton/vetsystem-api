package com.mj.vetsystem.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PacienteResumoModel {

    private Long id;
    private String nome;
    private ClienteResumoModel dono;
    
}