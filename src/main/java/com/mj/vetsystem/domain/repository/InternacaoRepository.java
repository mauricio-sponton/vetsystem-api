package com.mj.vetsystem.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mj.vetsystem.domain.model.Internacao;

@Repository
public interface InternacaoRepository extends JpaRepository<Internacao, Long>{

	@Query(value = "select i from Internacao i join fetch i.paciente p join fetch p.dono join fetch p.raca r join fetch r.especie join fetch i.tratamentos", countQuery = "select count(i.id) from Internacao i")
	Page<Internacao> findAll(Pageable pageable);

	@Query("select count(i.id) > 0 from Internacao i where i.paciente.id = :pacienteId and i.status = 'ATIVA'")
	boolean isPacienteInternado(Long pacienteId);
}
