package com.mj.vetsystem.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mj.vetsystem.domain.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	@Query(value = "select c from Cidade c left join fetch c.estado", countQuery = "select count(c.id) from Cidade c")
	Page<Cidade> findAll(Pageable pageable);
}
