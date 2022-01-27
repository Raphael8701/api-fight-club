package com.academia.fightclub.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.academia.fightclub.domain.exception.EntidadeEmUsoException;
import com.academia.fightclub.domain.exception.EntidadeNaoEncontradaException;
import com.academia.fightclub.domain.model.EstiloDeLuta;
import com.academia.fightclub.domain.repository.EstiloDeLutaRepository;

@Service
public class CadastroEstiloDeLutaService {
	@Autowired
	private EstiloDeLutaRepository estiloDeLutaRepository;

	public EstiloDeLuta salvar(EstiloDeLuta estiloDeLuta) {
		return estiloDeLutaRepository.save(estiloDeLuta);
	}

	public void excluir(Long estiloDeLutaId) {
		
		try {
		estiloDeLutaRepository.deleteById(estiloDeLutaId);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de luta com código %d", estiloDeLutaId));
		}
		 catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(
							" Estilo de luta de código %d não pode ser removido, pois está em uso ", estiloDeLutaId));
		}
	
		}

}
