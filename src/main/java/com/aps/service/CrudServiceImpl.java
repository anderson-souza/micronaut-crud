package com.aps.service;

import com.aps.model.DefaultEntity;
import io.micronaut.data.repository.CrudRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class CrudServiceImpl<T extends DefaultEntity> implements CrudService<T> {

    private final CrudRepository<T, Long> defaultCrudRepository;

    protected CrudServiceImpl(CrudRepository<T, Long> defaultCrudRepository) {
        this.defaultCrudRepository = defaultCrudRepository;
    }

    @Override
    public Iterable<T> getAll() {
        return defaultCrudRepository.findAll();
    }

    @Override
    public T getById(Long id) {
        preGetById(id);
        return defaultCrudRepository.findById(id).orElse(null);
    }

    @Override
    public T save(T t) {
        preSave(t);
        return defaultCrudRepository.save(t);
    }

    @Override
    public T update(Long id, T t) {
        preUpdate(id, t);
        t.setId(id);
        return defaultCrudRepository.update(t);
    }

    @Override
    public void deleteById(Long id) {
        preDelete(id);
        defaultCrudRepository.deleteById(id);
    }

    @Override
    public void preGetById(Long id) {
        checkNotNull(id, "Id não pode ser Nulo");
    }

    @Override
    public void preSave(T t) {
        checkNotNull(t, "Entidade não pode ser nulo");
    }

    @Override
    public void preUpdate(Long id, T t) {
        checkNotNull(id, "Id não pode ser nulo");
        checkNotNull(t, "Entidade não pode ser nula");
    }

    @Override
    public void preDelete(Long id) {
        checkNotNull(id, "Id não pode ser nulo");
    }
}
