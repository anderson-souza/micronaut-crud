package com.aps.service;

import com.aps.model.Pessoa;
import io.micronaut.data.repository.CrudRepository;

import javax.inject.Singleton;

@Singleton
public class PessoaService extends CrudServiceImpl<Pessoa> {

    public PessoaService(CrudRepository<Pessoa, Long> defaultCrudRepository) {
        super(defaultCrudRepository);
    }
}
