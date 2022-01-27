package com.academia.fightclub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.academia.fightclub.domain.exception.EntidadeEmUsoException;
import com.academia.fightclub.domain.exception.EntidadeNaoEncontradaException;
import com.academia.fightclub.domain.model.EstiloDeLuta;
import com.academia.fightclub.domain.repository.EstiloDeLutaRepository;
import com.academia.fightclub.domain.service.CadastroEstiloDeLutaService;

@RestController
@RequestMapping(value = "/lutas")
public class EstiloDeLutaController {
	@Autowired
	private EstiloDeLutaRepository estiloDeLutaRepository; 
	
	@Autowired
	private CadastroEstiloDeLutaService cadastroLuta;
	
	@GetMapping
	public List<EstiloDeLuta> listar() {
		return estiloDeLutaRepository.findAll();
	}
	
	
	@GetMapping("/{lutaId}")
	public ResponseEntity<EstiloDeLuta> buscar(@PathVariable Long lutaId) {
		Optional <EstiloDeLuta> estiloDeLuta = estiloDeLutaRepository.findById(lutaId);

		if (estiloDeLuta.isPresent()) {
			return ResponseEntity.ok(estiloDeLuta.get());
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstiloDeLuta adicionar(@RequestBody EstiloDeLuta estiloDeLuta) {
		return cadastroLuta.salvar(estiloDeLuta);

	}

	@PutMapping("/{lutaId}")
	public ResponseEntity<EstiloDeLuta> atualizar(@PathVariable Long lutaId, @RequestBody EstiloDeLuta estiloDeLuta) {
		Optional <EstiloDeLuta> estiloDeLutaAtual = estiloDeLutaRepository.findById(lutaId);

		if (estiloDeLutaAtual.isPresent()) {
			BeanUtils.copyProperties(estiloDeLuta, estiloDeLutaAtual.get(), "id");
			EstiloDeLuta estiloDeLutaSalvo = cadastroLuta.salvar(estiloDeLutaAtual.get());
			return ResponseEntity.ok(estiloDeLutaSalvo);

		}

		return ResponseEntity.notFound().build();

	}

	
	
	@DeleteMapping("/{alunoId}")
	public ResponseEntity<EstiloDeLuta> remover(@PathVariable Long lutaId) {
		
			try {
			cadastroLuta.excluir(lutaId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.notFound().build();

			} catch (EntidadeEmUsoException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
			
		
	}
}
