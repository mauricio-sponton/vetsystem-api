package com.mj.vetsystem.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mj.vetsystem.domain.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	@Query("select c from Cidade c join fetch c.estado")
	List<Cidade> findAll();
}
