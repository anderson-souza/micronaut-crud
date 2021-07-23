package com.aps.repository;

import com.aps.model.Pessoa;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
}
