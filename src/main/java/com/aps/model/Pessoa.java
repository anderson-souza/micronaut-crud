package com.aps.model;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pessoa")
public class Pessoa extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode ser nulo ou vazio")
    @Size(min = 2, message = "Nome deve conter pelo menos 2 caracteres")
    private String nome;

    @Column(name = "cidade_nascimento")
    private String cidadeNascimento;

    @Column
    private Boolean ativo = Boolean.TRUE;
}
