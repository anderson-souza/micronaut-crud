package com.aps.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.aps.architecture.PackagesDefinition.DEFAULT_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.GeneralCodingRules.ACCESS_STANDARD_STREAMS;
import static com.tngtech.archunit.library.GeneralCodingRules.THROW_GENERIC_EXCEPTIONS;

class CodingStandardsTest {

    private JavaClasses importedClasses;

    @BeforeEach
    void setUp() {
        importedClasses = new ClassFileImporter().importPackages(DEFAULT_PACKAGE);
    }

    @Test
    void no_access_to_standard_streams_as_method() {
        ArchRule rule = noClasses().should(ACCESS_STANDARD_STREAMS)
                .because("Não se deve utilizar println para registros de log e debug. " +
                        "Utilizar sistema de logs específico para isso");

        rule.check(importedClasses);
    }

    @Test
    void classes_should_not_throw_generic_exceptions() {
        ArchRule rule = noClasses().should(THROW_GENERIC_EXCEPTIONS)
                .because("Classes não devem lançar exceções genéricas. Criar uma exceção específica para retorno");

        rule.check(importedClasses);
    }

}
