package com.example.muji.validation;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Class with validation constraints
 */
@Data
@Builder
public class Person {

  @NotEmpty
  @Size(min = 3, max = 10, message = "Name must be between 3 and 10 characters")
  private String name;

  @Min(value = 0, message = "Age should not be less than 0")
  @Max(value = 90, message = "Age should not be greater than 90")
  private int age;
}
