package com.mj.vetsystem.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.domain.event.PacienteSalvoEvent;
import com.mj.vetsystem.domain.model.HistoricoPeso;
import com.mj.vetsystem.domain.model.Paciente;
import com.mj.vetsystem.domain.repository.PacienteRepository;

@Component
public class RegistroHistoricoPesoListener {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@EventListener
	public void aoSalvarPaciente(PacienteSalvoEvent event) {
		Paciente paciente = event.getPaciente();

		var historicoPeso = new HistoricoPeso();
		historicoPeso.setPeso(paciente.getPeso());
		historicoPeso.setPaciente(paciente);

		pacienteRepository.save(historicoPeso);
	}
}
