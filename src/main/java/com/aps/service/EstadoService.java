package com.aps.service;

import com.aps.model.Estado;
import io.micronaut.data.repository.CrudRepository;

public class EstadoService extends CrudServiceImpl<Estado> {

    protected EstadoService(CrudRepository<Estado, Long> defaultCrudRepository) {
        super(defaultCrudRepository);
    }
}
