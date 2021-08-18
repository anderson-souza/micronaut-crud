package com.aps.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import org.junit.jupiter.api.Test;

import static com.aps.architecture.PackagesDefinition.DEFAULT_PACKAGE;
import static com.aps.architecture.PackagesDefinition.RESOURCES_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

class RestResourceTest {

    private final JavaClasses importedClasses = new ClassFileImporter().importPackages(DEFAULT_PACKAGE);

    @Test
    void get_methods_should_return_http_response() {
        methods().that().areDeclaredInClassesThat().resideInAPackage(RESOURCES_PACKAGE)
                .and().areAnnotatedWith(Get.class)
                .should()
                .haveRawReturnType(HttpResponse.class)
                .because("Métodos GET devem retornar um encapsulamento de HttpResponse")
                .check(importedClasses);

    }

}
