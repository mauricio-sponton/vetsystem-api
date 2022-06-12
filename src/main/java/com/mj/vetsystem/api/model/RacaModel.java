package com.mj.vetsystem.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RacaModel {

    private Long id;
    private String nome;
    private EspecieModel especie;
    
}  