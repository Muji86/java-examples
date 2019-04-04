package com.example.muji.validation;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersonValidationTest {

  private ValidatorFactory factory;
  private Validator validator;

  @Before
  public void setup() {
    factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void validPersonValidation_test() {
    // Given
    Person hajar = Person.builder().age(4).name("Hajar").build();

    // When
    Set<ConstraintViolation<Person>> violations = validator.validate(hajar);

    // Then
    assertThat(violations.size()).isEqualTo(0);
  }

  @Test
  public void invalidPersonValidation_test() {
    // Given
    Person hajar = Person.builder().age(100).name("Hajar").build();

    // When
    Set<ConstraintViolation<Person>> violations = validator.validate(hajar);

    // Then
    assertThat(violations.size()).isEqualTo(1);
    List<String> violationMessages = violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());
    assertThat(violationMessages.toArray()).containsExactly("Age should not be greater than 90");
  }
}
