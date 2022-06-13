package com.mj.vetsystem.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mj.vetsystem.domain.model.Especie;

@Repository
public interface EspecieRepository extends CustomJpaRepository<Especie, Long>{

	@Query("select e from Especie e where e.nome = :nome")
	Optional<Especie> findByNome(String nome);

}
