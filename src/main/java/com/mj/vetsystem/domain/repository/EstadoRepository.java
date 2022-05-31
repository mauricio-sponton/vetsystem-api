package com.mj.vetsystem.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mj.vetsystem.domain.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
