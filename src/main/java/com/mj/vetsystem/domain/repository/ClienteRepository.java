package com.mj.vetsystem.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mj.vetsystem.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query(value = "select c from Cliente c left join fetch c.endereco.cidade cidade left join fetch cidade.estado", countQuery = "select count(c.id) from Cliente c")
	Page<Cliente> findAll(Pageable pageable);
}
