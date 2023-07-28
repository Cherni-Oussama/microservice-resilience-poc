package com.example.product.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.example.product", importOptions = {
    ImportOption.DoNotIncludeTests.class})
class ArchitectureDomainTest {

  @ArchTest
  public ArchRule domainRule =
      noClasses()
          .that()
          .resideInAnyPackage("..domain..")
          .should().dependOnClassesThat()
          .resideInAnyPackage("..application..", "..infrastructure..");

  @ArchTest
  public ArchRule appRule =
      noClasses()
          .that()
          .resideInAnyPackage("..application..")
          .should().dependOnClassesThat()
          .resideInAnyPackage("..infrastructure..");

  @ArchTest
  public ArchRule infraRule =
      noClasses()
          .that()
          .resideInAnyPackage("..infrastructure..")
          .should().dependOnClassesThat()
          .resideInAnyPackage("..application..");


}