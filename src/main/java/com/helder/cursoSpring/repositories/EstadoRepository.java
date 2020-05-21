package com.helder.cursoSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helder.cursoSpring.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	
}
