package com.aps.resources.crud;

import com.aps.model.DefaultEntity;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;

public interface CrudResource<T extends DefaultEntity> {

    /**
     * Função que retornará todas as entidades de T
     *
     * @return Iterable de T
     */
    Iterable<T> getAll();

    HttpResponse<Page<T>> getAllPaginated(Pageable pageable);

    /**
     * Função que retornará uma entidade de T pelo seu ID
     *
     * @param id ID da Entidade
     * @return Entidade
     */
    T getById(Long id);

    /**
     * @param t Entidade
     * @return Entidade persistida no banco de dados
     */
    T save(T t);

    /**
     * @param id ID da entidade
     * @param t Entidade
     * @return Entidade persistida no banco de dados
     */
    T update(Long id, T t);

    /**
     * @param id ID da entidade
     */
    void delete(Long id);
}
