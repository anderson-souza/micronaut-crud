package com.aps;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info =
                @Info(
                        title = "CRUD Demo",
                        version = "0.1",
                        description = "API de Crud com Micronaut",
                        contact = @Contact(name = "Anderson", email = "andersonpds14@gmail.com")))
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
