package com.aps.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import io.micronaut.data.annotation.Repository;
import io.micronaut.http.annotation.Controller;
import org.junit.jupiter.api.Test;

import javax.persistence.Entity;

import static com.aps.architecture.PackagesDefinition.DEFAULT_PACKAGE;
import static com.aps.architecture.PackagesDefinition.DOMAIN_PACKAGE;
import static com.aps.architecture.PackagesDefinition.REPOSITORY_PACKAGE;
import static com.aps.architecture.PackagesDefinition.RESOURCES_PACKAGE;
import static com.aps.architecture.PackagesDefinition.SERVICE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class PackageResideTest {

    private final JavaClasses importedClasses = new ClassFileImporter().importPackages(DEFAULT_PACKAGE);

    @Test
    void repositories_should_reside_in_repository_package() {
        classes().that().areAnnotatedWith(Repository.class)
                .or().haveNameMatching(".*Repository")
                .should()
                .resideInAPackage(REPOSITORY_PACKAGE)
                .check(importedClasses);
    }

    @Test
    void services_should_reside_in_service_package() {
        classes().that().haveNameMatching(".*Service")
                .should()
                .resideInAPackage(SERVICE_PACKAGE)
                .check(importedClasses);
    }

    @Test
    void controllers_should_reside_in_resources_package() {
        classes().that().areAnnotatedWith(Controller.class)
                .or().haveNameMatching(".*Resource")
                .or().haveNameMatching(".*Controller")
                .should()
                .resideInAPackage(RESOURCES_PACKAGE)
                .check(importedClasses);
    }

    @Test
    void domain_classes_should_reside_in_model_package() {
        classes().that().areAnnotatedWith(Entity.class)
                .should()
                .resideInAPackage(DOMAIN_PACKAGE)
                .check(importedClasses);
    }

}
