package com.mj.vetsystem.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mj.vetsystem.domain.model.Dono;

@Repository
public interface DonoRepository extends JpaRepository<Dono, Long>{

	@Query(value = "select d from Dono d left join fetch d.endereco.cidade c left join fetch c.estado", countQuery = "select count(d.id) from Dono d")
	Page<Dono> findAll(Pageable pageable);
}
