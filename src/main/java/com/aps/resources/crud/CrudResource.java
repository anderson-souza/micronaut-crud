package com.aps.resources.crud;

import com.aps.model.DefaultEntity;

public interface CrudResource<T extends DefaultEntity> {

    /**
     * Função que retornará todas as entidades de T
     *
     * @return Iterable de T
     */
    Iterable<T> getAll();

    /**
     * Função que retornará uma entidade de T pelo seu ID
     *
     * @param id ID da Entidade
     * @return Entidade
     */
    T getById(Long id);

    /**
     * @param t
     * @return
     */
    T save(T t);

    /**
     * @param id
     * @param t
     * @return
     */
    T update(Long id, T t);

    /** @param id */
    void delete(Long id);
}
