package com.mj.vetsystem.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mj.vetsystem.domain.model.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long>{

}
