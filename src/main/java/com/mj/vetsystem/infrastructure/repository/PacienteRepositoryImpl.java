package com.mj.vetsystem.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mj.vetsystem.domain.model.HistoricoPeso;
import com.mj.vetsystem.domain.repository.PacienteRepositoryQueries;

@Repository
public class PacienteRepositoryImpl implements PacienteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public HistoricoPeso save(HistoricoPeso historico) {
		return manager.merge(historico);
	}

}
