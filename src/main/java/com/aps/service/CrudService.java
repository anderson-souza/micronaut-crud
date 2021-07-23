package com.aps.service;

import com.aps.model.DefaultEntity;

public interface CrudService<T extends DefaultEntity> {

    /**
     * Função que retornará todas as entidades encontradas no banco
     *
     * @return Iterable de T, sendo T
     */
    Iterable<T> getAll();

    /**
     * Função disparada antes de executar a função getById
     *
     * @param id Id do elemento que se deseja buscar
     */
    void preGetById(Long id);

    /**
     * Função que retorna uma entidade através de seu ID no banco
     *
     * @param id ID da entidade
     * @return Entidade caso encontre, do contrário retorna null
     */
    T getById(Long id);

    /**
     * Função disparada antes da execução do Save da entidade
     *
     * @param t Entidade a ser salva
     */
    void preSave(T t);

    /**
     * Executa o save da entidade no banco de dados
     *
     * @param t Entidade a ser persistida
     * @return Entidade já persistida no banco de dados
     */
    T save(T t);

    /**
     * Função disparada antes do update da entidade no banco de dados
     *
     * @param id ID da entidade
     * @param t  Entidade a ser atualizada
     */
    void preUpdate(Long id, T t);

    /**
     * Função que executa um update da entidade no banco de dados
     *
     * @param id ID da entidade
     * @param t  Entidade a ser atualizada
     * @return Entidade já atualizada no banco de dados
     */
    T update(Long id, T t);

    /**
     * Função disparada antes da execução de delete no banco de dados
     *
     * @param id ID do objeto que se deseja persistir
     */
    void preDelete(Long id);

    /**
     * Função que faz o delete da entidade no banco de dados
     *
     * @param id ID da entidade
     */
    void deleteById(Long id);

}
