package com.aps.resources.crud;

import com.aps.model.DefaultEntity;
import com.aps.service.crud.CrudService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Status;
import io.micronaut.validation.Validated;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Classe responsável por implementar métodos de CRUD padrão para um Resource. Esta classe expõe os
 * métodos padrões de um CRUD utilizando de endpoints padronizados
 *
 * @param <T> Classe que estende de DefaultEntity
 */
public abstract class CrudResourceImpl<T extends DefaultEntity> implements CrudResource<T> {

    private final CrudService<T> crudService;

    protected CrudResourceImpl(CrudService<T> crudService) {
        this.crudService = crudService;
    }

    /** @return Iterable de entidades persistidas no banco */
    @Get(produces = MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Busca todas as entidades",
            description = "Endpoint retornará uma coleção de todas as entidades")
    @Override
    public Iterable<T> getAll() {
        return crudService.getAll();
    }

    @Get(produces = MediaType.APPLICATION_JSON, value = "/paginated")
    @Override
    public HttpResponse<Page<T>> getAllPaginated(Pageable pageable) {
        return HttpResponse.ok().body(crudService.getAllPaginated(pageable));
    }

    /**
     * @param id ID da Entidade
     * @return Entidade
     */
    @Get(produces = MediaType.APPLICATION_JSON, value = "/{id}")
    @Operation(
            summary = "Busca entidade por ID",
            description =
                    "Endpoint retornará a entidade completa através de seu ID caso seja encontrada")
    @Override
    public T getById(Long id) {
        return crudService.getById(id);
    }

    /**
     * @param t Entidade a ser salva
     * @return Entidade persistida no banco de dados
     */
    @Post
    @Status(HttpStatus.CREATED)
    @Validated
    @Operation(
            summary = "Salva a entidade no banco",
            description = "Endpoint responsável por salvar a entidade no banco de dados")
    @Override
    public T save(T t) {
        return crudService.save(t);
    }

    /**
     * @param id ID da Entidade
     * @param t Entidade a ser persistida
     * @return Entidade persistida no banco de dados
     */
    @Put("/{id}")
    @Validated
    @Operation(
            summary = "Atualiza a entidade no banco",
            description = "Endpoint responsável por atualizar a entidade no banco de dados")
    @Override
    public T update(Long id, T t) {
        return crudService.update(id, t);
    }

    /** @param id ID da entidade */
    @Delete("/{id}")
    @Operation(
            summary = "Deleta a entidade no banco",
            description =
                    "Endpoint responsável por deletar a entidade no banco de dados, caso ela exista")
    @Override
    public void delete(Long id) {
        crudService.deleteById(id);
    }
}
