package com.mj.vetsystem.domain.event;

import com.mj.vetsystem.domain.model.Paciente;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PacienteSalvoEvent {

	private Paciente paciente;
}
