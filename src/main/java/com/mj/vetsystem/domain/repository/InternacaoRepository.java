package com.mj.vetsystem.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mj.vetsystem.domain.model.Internacao;

@Repository
public interface InternacaoRepository extends JpaRepository<Internacao, Long>{

//	@Query(value = "select c from Cidade c left join fetch c.estado", countQuery = "select count(c.id) from Cidade c")
//	Page<Cidade> findAll(Pageable pageable);
}
