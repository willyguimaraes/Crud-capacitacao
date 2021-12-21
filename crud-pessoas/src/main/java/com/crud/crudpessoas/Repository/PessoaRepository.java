package com.crud.crudpessoas.Repository;

import java.util.List;
import com.crud.crudpessoas.Model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByCpf(String cpf);
}


