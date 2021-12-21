package com.crud.crudpessoas.Controller;

import java.util.List;
import java.util.Optional;

import com.crud.Util.CustomError;
import com.crud.crudpessoas.Model.Pessoa;
import com.crud.crudpessoas.Repository.PessoaRepository;
import com.crud.crudpessoas.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/pessoa")
@AllArgsConstructor
public class PessoaController {

    
    PessoaService service;
    PessoaRepository repository;

    /**  Cadastra uma nova pessoa.
    @PostMapping
    public ResponseEntity<?> cadastrarPessoa(String cpf, String nome, String endereco, String idade, String telefone) {
        //System.out.println(nome);
        Pessoa pessoa = service.cadastrarPessoa(cpf, nome, endereco, idade, telefone);
        //System.out.println(pessoa);

        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }
    */

 
    @PostMapping
    public ResponseEntity<?> cadastrarPessoa(@RequestBody Pessoa pessoa, UriComponentsBuilder ucBuilder) {

       List<Pessoa> pessoas = repository.findByCpf(pessoa.getCpf());

        if (!pessoas.isEmpty()) {
            return new ResponseEntity<CustomError>(new CustomError("ERRO"), HttpStatus.CONFLICT);
        }

        repository.save(pessoa);
        return new ResponseEntity<Pessoa>(pessoa, HttpStatus.CREATED);
    }





    // Atualiza os dados de uma pessoa.
    @RequestMapping(value = "Pessoa/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePessoa(@PathVariable("id") Long id, @RequestParam String cpf, String nome,
            String endereco, String idade, String telefone) {

        Pessoa pessoa = service.update(id, cpf, nome, endereco, idade, telefone);

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    // Exibe todos as pessoas cadastradas.
    @RequestMapping(value = "Pessoa/listar", method = RequestMethod.GET)
    public ResponseEntity<?> listarPessoas() {

        List pessoas = service.findAll();

        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    // Exibe uma pessoa cadastrada, pelo seu id.
    @RequestMapping(value = "Pessoa/exibir/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarPessoa(@PathVariable("id") Long id) {

        Pessoa pessoa = service.findById(id);

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    // Exibe uma pessoa cadastrada, pelo seu id.
    @RequestMapping(value = "Pessoa/deletar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletarPessoa(@PathVariable("id") Long id) {

        Pessoa pessoa = service.delete(id);

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

}
