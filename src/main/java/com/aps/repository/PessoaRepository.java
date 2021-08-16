package com.aps.repository;

import com.aps.model.Pessoa;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;

@Repository
public interface PessoaRepository extends PageableRepository<Pessoa, Long> {
}
