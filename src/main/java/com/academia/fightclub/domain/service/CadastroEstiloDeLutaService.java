package com.academia.fightclub.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		estiloDeLutaRepository.deleteById(estiloDeLutaId);

	}

}
