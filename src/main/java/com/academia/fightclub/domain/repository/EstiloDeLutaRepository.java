package com.academia.fightclub.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.fightclub.domain.model.EstiloDeLuta;

@Repository
public interface EstiloDeLutaRepository extends JpaRepository<EstiloDeLuta, Long >{

}
