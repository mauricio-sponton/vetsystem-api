package com.mj.vetsystem.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mj.vetsystem.domain.model.HistoricoPeso;
import com.mj.vetsystem.domain.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>, PacienteRepositoryQueries{

	@Query("select h from HistoricoPeso h where h.paciente.id = :pacienteId")
	List<HistoricoPeso> findAllByPaciente(Long pacienteId);
	
	@Query(value = "select p from Paciente p join fetch p.dono join fetch p.raca r join fetch r.especie", countQuery = "select count(p.id) from Paciente p")
	Page<Paciente> findAll(Pageable pageable);
}
