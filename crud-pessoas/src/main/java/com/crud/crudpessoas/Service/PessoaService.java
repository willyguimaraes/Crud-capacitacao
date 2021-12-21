package com.crud.crudpessoas.Service;

import java.util.List;

import com.crud.Util.ResourceNotFoundException;
import com.crud.crudpessoas.Model.Pessoa;
import com.crud.crudpessoas.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa cadastrarPessoa(String cpf, String nome, String endereco, String idade, String telefone) {
        Pessoa pessoa = new Pessoa(cpf, nome, endereco, idade, telefone);
        repository.save(pessoa);
        return pessoa;
    }

    public Pessoa update(Long id, String cpf, String nome, String endereco, String idade, String telefone) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não encontrado / cadastrado"));
        pessoa.setCpf(cpf);
        pessoa.setNome(nome);
        pessoa.setEndereco(endereco);
        pessoa.setidade(idade);
        pessoa.setTelefone(telefone);
        repository.save(pessoa);
        return pessoa;
    }

    public Pessoa findById(long id) {
        java.util.Optional<Pessoa> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado / cadastrado"));
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public Pessoa delete(long id) {
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado / cadastrado"));
        repository.delete(pessoa);
        return pessoa;
    }

}
