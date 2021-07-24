package com.aps.service;

import com.aps.model.Pessoa;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@MicronautTest
class PessoaServiceTest {

    @Inject
    private PessoaService pessoaService;

    Pessoa pessoa;

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa();
    }

    @Test
    void testSavePessoaWithName() {

        pessoa.setNome("Joaquin do teste");

        Pessoa p = pessoaService.save(pessoa);
        assertThat(p, notNullValue());
    }

    @Test
    void testSavePessoaWithoutNameExpectException() {

        Assertions.assertThrows(ConstraintViolationException.class, () -> pessoaService.save(pessoa));
    }

    @Test
    void testSavePessoaWithNameMinimumLengthNotAllowedExpectException() {

        pessoa.setNome("A");
        Exception exception = Assertions.assertThrows(ConstraintViolationException.class, () -> pessoaService.save(pessoa));

        assertTrue(exception.getMessage().contains("Nome deve conter pelo menos 2 caracteres"));
    }

    @Test
    void testSavePessoaWithEmptyNameExpectException() {

        pessoa.setNome("         ");
        Exception exception = Assertions.assertThrows(ConstraintViolationException.class, () -> pessoaService.save(pessoa));

        assertTrue(exception.getMessage().contains("Nome não pode ser nulo ou vazio"));
    }


    @Test
    void getAll() {

        pessoaService = Mockito.mock(PessoaService.class);
        when(pessoaService.getAll()).thenReturn(new ArrayList<>(Collections.singletonList(pessoa)));

        Iterable<Pessoa> pessoas = pessoaService.getAll();

        assertThat(pessoas, notNullValue());
        assertThat(pessoas, is(not(emptyIterable())));
    }

    @Test
    void getById() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}