package com.crud.crudpessoas.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pessoa_cpf", unique = true)
    private String cpf;
    @Column(name = "pessoa_nome")
    private String nome;
    @Column(name = "pessoa_endereco")
    private String endereco;
    @Column(name = "pessoa_idade")
    private String idade;
    @Column(name = "pessoa_telefone")
    private String telefone;

    public Pessoa() {
    }

    public Pessoa(String cpf, String nome, String endereco, String idade, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.idade = idade;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getidade() {
        return idade;
    }

    public void setidade(String idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return "Pessoa [cpf=" + cpf + ", id=" + id + ", nome = " + nome + "]";
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
