package com.crud.crudpessoas.Controller;

import java.util.List;
import com.crud.Util.CustomError;
import com.crud.Util.ResourceNotFoundException;
import com.crud.crudpessoas.Model.Pessoa;
import com.crud.crudpessoas.Repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/pessoa")
@AllArgsConstructor
public class PessoaController {

    
    PessoaRepository repository;

    //Cadastra uma nova pessoa
    @PostMapping
    public ResponseEntity<?> cadastrarPessoa(@RequestBody Pessoa pessoa, UriComponentsBuilder ucBuilder) {

        List<Pessoa> pessoas = repository.findByCpf(pessoa.getCpf());

        if (!pessoas.isEmpty()) {
            return new ResponseEntity<CustomError>(new CustomError("ERRO! Pessoa já cadastrada"), HttpStatus.CONFLICT);
        }

        repository.save(pessoa);
        return new ResponseEntity<Pessoa>(pessoa, HttpStatus.CREATED);
    }

    // Atualiza os dados de uma pessoa.
    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePessoa(@PathVariable("id") Long id, @RequestBody Pessoa p) {

        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não encontrado / cadastrado"));

        pessoa.setCpf(p.getCpf());
        pessoa.setNome(p.getNome());
        pessoa.setEndereco(p.getEndereco());
        pessoa.setidade(p.getidade());
        pessoa.setTelefone(p.getTelefone());
        repository.save(pessoa);

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    // Exibe todos as pessoas cadastradas.
    @RequestMapping(value = "listar", method = RequestMethod.GET)
    public ResponseEntity<?> listarPessoas() {

        List pessoas = repository.findAll();

        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    // Exibe uma pessoa cadastrada, pelo seu id.
    @RequestMapping(value = "exibir/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarPessoa(@PathVariable("id") Long id) {

        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não encontrado / cadastrado"));

        return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
    }

    // Deleta uma pessoa cadastrada, pelo seu id.
    @RequestMapping(value = "deletar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletarPessoa(@PathVariable("id") Long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não encontrado / cadastrado"));
        repository.delete(pessoa);

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

}
