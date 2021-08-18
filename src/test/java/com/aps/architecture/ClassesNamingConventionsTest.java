package com.aps.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import io.micronaut.data.annotation.Repository;
import io.micronaut.http.annotation.Controller;
import org.junit.jupiter.api.Test;

import javax.inject.Singleton;

import static com.aps.architecture.PackagesDefinition.DEFAULT_PACKAGE;
import static com.aps.architecture.PackagesDefinition.REPOSITORY_PACKAGE;
import static com.aps.architecture.PackagesDefinition.RESOURCES_PACKAGE;
import static com.aps.architecture.PackagesDefinition.SERVICE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class ClassesNamingConventionsTest {

    private final JavaClasses importedClasses = new ClassFileImporter().importPackages(DEFAULT_PACKAGE);

    @Test
    void services_classes_should_contain_service_name() {
        classes().that().resideInAPackage(SERVICE_PACKAGE)
                .and().areAnnotatedWith(Singleton.class)
                .should().haveSimpleNameContaining("Service").check(importedClasses);
    }

    @Test
    void repositories_classes_should_contain_repository_name() {
        classes().that().resideInAPackage(REPOSITORY_PACKAGE)
                .and().areAnnotatedWith(Repository.class)
                .should().haveSimpleNameContaining("Repository").check(importedClasses);
    }

    @Test
    void controller_classes_should_contain_resource_name() {
        classes().that().resideInAPackage(RESOURCES_PACKAGE)
                .and().areAnnotatedWith(Controller.class)
                .should().haveSimpleNameContaining("Resource").check(importedClasses);
    }
}
