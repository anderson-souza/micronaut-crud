package com.aps.resources;

import com.aps.exceptions.NotAllowedException;
import com.aps.model.Pessoa;
import com.aps.resources.crud.CrudResourceImpl;
import com.aps.service.crud.CrudService;
import io.micronaut.http.annotation.Controller;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pessoa")
@Controller("/pessoa")
public class PessoaResource extends CrudResourceImpl<Pessoa> {

    public PessoaResource(CrudService<Pessoa> crudService) {
        super(crudService);
    }

    @Override
    public void delete(Long id) {
        throw new NotAllowedException();
    }
}
