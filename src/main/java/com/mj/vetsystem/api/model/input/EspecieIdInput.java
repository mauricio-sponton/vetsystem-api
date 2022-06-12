package com.mj.vetsystem.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EspecieIdInput {

    @NotNull
    private Long id;
    
}