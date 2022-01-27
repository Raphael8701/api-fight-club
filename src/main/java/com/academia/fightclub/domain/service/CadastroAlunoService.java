package com.academia.fightclub.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.academia.fightclub.domain.exception.EntidadeEmUsoException;
import com.academia.fightclub.domain.exception.EntidadeNaoEncontradaException;
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

		try {
		alunoRepository.deleteById(alunoId);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de aluno com código %d", alunoId));
		}
		 catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(
							" Aluno de código %d não pode ser removido, pois está em uso ", alunoId));
		}
		
	}

}
