package com.mj.vetsystem.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mj.vetsystem.domain.model.Dono;

@Repository
public interface DonoRepository extends JpaRepository<Dono, Long>{

	@Query("select d from Dono d join fetch d.endereco.cidade")
	List<Dono> findAll();
}
