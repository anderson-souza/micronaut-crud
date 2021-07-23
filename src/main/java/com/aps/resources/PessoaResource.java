package com.aps.resources;

import com.aps.model.Pessoa;
import com.aps.service.PessoaService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Status;
import io.micronaut.validation.Validated;

@Controller("/pessoa")
public class PessoaResource {

    private final PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public Iterable<Pessoa> getPessoa() {
        return pessoaService.getAll();
    }

    @Get(produces = MediaType.APPLICATION_JSON, value = "/{id}")
    public Pessoa getPessoaById(Long id) {
        return pessoaService.getById(id);
    }

    @Post
    @Status(HttpStatus.CREATED)
    @Validated
    public Pessoa savePessoa(@Body Pessoa pessoa) {
        return pessoaService.save(pessoa);
    }

    @Put("/{id}")
    public Pessoa updatePessoa(Long id, @Body Pessoa pessoa) {
        return pessoaService.update(id, pessoa);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void deletePessoa(Long id) {
        pessoaService.deleteById(id);
    }

}
