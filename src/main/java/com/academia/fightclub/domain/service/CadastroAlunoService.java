package com.academia.fightclub.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.fightclub.domain.model.Aluno;
import com.academia.fightclub.domain.repository.AlunoRepository;

@Service
public class CadastroAlunoService {
	@Autowired
	private AlunoRepository alunoRepository;

	public Aluno salvar(Aluno aluno) {
		return alunoRepository.save(aluno);
	}

	public void excluir(Long alunoId) {

		alunoRepository.deleteById(alunoId);

	}

}
