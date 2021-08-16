package com.aps.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import io.micronaut.data.annotation.Repository;
import io.micronaut.http.annotation.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.aps.architecture.PackagesDefinition.DEFAULT_PACKAGE;
import static com.aps.architecture.PackagesDefinition.REPOSITORY_PACKAGE;
import static com.aps.architecture.PackagesDefinition.RESOURCES_PACKAGE;
import static com.aps.architecture.PackagesDefinition.SERVICE_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class PackageResideTest {

    private JavaClasses importedClasses;

    @BeforeEach
    void setUp() {
        importedClasses = new ClassFileImporter().importPackages(DEFAULT_PACKAGE);
    }

    @Test
    void repositories_should_reside_in_repository_package() {
        ArchRule rule = classes().that().areAnnotatedWith(Repository.class)
                .or().haveNameMatching(".*Repository")
                .should()
                .resideInAPackage(REPOSITORY_PACKAGE);

        rule.check(importedClasses);
    }

    @Test
    void services_should_reside_in_service_package() {
        ArchRule rule = classes().that().haveNameMatching(".*Service")
                .should()
                .resideInAPackage(SERVICE_PACKAGE);

        rule.check(importedClasses);
    }

    @Test
    void controllers_should_reside_in_resources_package() {
        ArchRule rule = classes().that().areAnnotatedWith(Controller.class)
                .or().haveNameMatching(".*Resource")
                .or().haveNameMatching(".*Controller")
                .should()
                .resideInAPackage(RESOURCES_PACKAGE);

        rule.check(importedClasses);
    }

}
