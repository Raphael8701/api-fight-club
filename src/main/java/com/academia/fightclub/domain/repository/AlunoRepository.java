package com.academia.fightclub.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.fightclub.domain.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long > {

}
