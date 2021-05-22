package com.github.youssfbr.logistica.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.youssfbr.logistica.domain.models.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long>{
	
}
