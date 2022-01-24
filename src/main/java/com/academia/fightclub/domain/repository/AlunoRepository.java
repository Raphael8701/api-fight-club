package com.academia.fightclub.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.fightclub.domain.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long > {

}
