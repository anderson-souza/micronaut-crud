package com.aps.resources.crud;

import com.aps.model.DefaultEntity;
import com.aps.service.crud.CrudService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Status;
import io.micronaut.validation.Validated;

/**
 * Classe responsável por implementar métodos de CRUD padrão para um Resource.
 * Esta classe expõe os métodos padrões de um CRUD utilizando de endpoints padronizados
 *
 * @param <T> Classe que extende de DefaultEntity
 */
public abstract class CrudResourceImpl<T extends DefaultEntity> implements CrudResource<T> {

    private final CrudService<T> crudService;

    protected CrudResourceImpl(CrudService<T> crudService) {
        this.crudService = crudService;
    }

    /**
     * @return
     */
    @Get(produces = MediaType.APPLICATION_JSON)
    @Override
    public Iterable<T> getAll() {
        return crudService.getAll();
    }

    /**
     * @param id ID da Entidade
     * @return
     */
    @Get(produces = MediaType.APPLICATION_JSON, value = "/{id}")
    @Override
    public T getById(Long id) {
        return crudService.getById(id);
    }

    /**
     * @param t
     * @return
     */
    @Post
    @Status(HttpStatus.CREATED)
    @Validated
    @Override
    public T save(T t) {
        return crudService.save(t);
    }

    /**
     * @param id
     * @param t
     * @return
     */
    @Put("/{id}")
    @Override
    public T update(Long id, T t) {
        return crudService.update(id, t);
    }

    /**
     * @param id
     */
    @Delete("/{id}")
    @Override
    public void delete(Long id) {
        crudService.deleteById(id);
    }
}
