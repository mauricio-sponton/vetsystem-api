package com.mj.vetsystem.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mj.vetsystem.domain.model.Cliente;

@Repository
public interface ClienteRepository extends CustomJpaRepository<Cliente, Long>{

	@Query(value = "select c from Cliente c left join fetch c.endereco.cidade cidade left join fetch cidade.estado", countQuery = "select count(c.id) from Cliente c")
	Page<Cliente> findAll(Pageable pageable);

	@Query("select c from Cliente c where c.email = :email")
	Optional<Cliente> findByEmail(String email);
	
	@Query("select c from Cliente c where c.cpf = :cpf")
	Optional<Cliente> findByCpf(String cpf);
}
