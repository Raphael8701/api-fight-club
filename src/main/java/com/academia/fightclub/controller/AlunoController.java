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
import com.academia.fightclub.domain.model.Aluno;
import com.academia.fightclub.domain.repository.AlunoRepository;
import com.academia.fightclub.domain.service.CadastroAlunoService;

@RestController
@RequestMapping(value = "/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepository; 
	
	@Autowired
	private CadastroAlunoService cadastroAluno;
	
	@GetMapping
	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}
	
	
	@GetMapping("/{alunoId}")
	public ResponseEntity<Aluno> buscar(@PathVariable Long alunoId) {
		Optional <Aluno> aluno = alunoRepository.findById(alunoId);

		if (aluno.isPresent()) {
			return ResponseEntity.ok(aluno.get());
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Aluno adicionar(@RequestBody Aluno aluno) {
		return cadastroAluno.salvar(aluno);

	}

	@PutMapping("/{alunoId}")
	public ResponseEntity<Aluno> atualizar(@PathVariable Long alunoId, @RequestBody Aluno aluno) {
		Optional <Aluno> alunoAtual = alunoRepository.findById(alunoId);

		if (alunoAtual.isPresent()) {
			BeanUtils.copyProperties(aluno, alunoAtual.get(), "id");
			Aluno alunoSalvo = cadastroAluno.salvar(alunoAtual.get());
			return ResponseEntity.ok(alunoSalvo);

		}

		return ResponseEntity.notFound().build();

	}

	
	
	@DeleteMapping("/{alunoId}")
	public ResponseEntity<Aluno> remover(@PathVariable Long alunoId) {
			try {
			cadastroAluno.excluir(alunoId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.notFound().build();

			} catch (EntidadeEmUsoException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		
	}

}
