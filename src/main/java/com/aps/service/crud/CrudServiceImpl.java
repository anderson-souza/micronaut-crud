package com.aps.service.crud;

import com.aps.exceptions.NotFoundException;
import com.aps.model.DefaultEntity;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class CrudServiceImpl<T extends DefaultEntity> implements CrudService<T> {

    private final PageableRepository<T, Long> defaultCrudRepository;

    protected CrudServiceImpl(PageableRepository<T, Long> defaultCrudRepository) {
        this.defaultCrudRepository = defaultCrudRepository;
    }

    @Override
    public Iterable<T> getAll() {
        return defaultCrudRepository.findAll();
    }

    @Override
    public Page<T> getAllPaginated(Pageable pageable) {
        return defaultCrudRepository.findAll(pageable);
    }

    @Override
    public T getById(Long id) throws NotFoundException {
        preGetById(id);
        return defaultCrudRepository.findById(id).orElseThrow(NotFoundException::new);
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
