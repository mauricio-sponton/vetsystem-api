package com.mj.vetsystem.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mj.vetsystem.domain.model.Raca;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Long>{

	@Query(value = "select r from Raca r left join fetch r.especie", countQuery = "select count(r.id) from Raca r")
	Page<Raca> findAll(Pageable pageable);
}
