package com.spasojetijanic.zavrsniprojekat.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person {

  @NotBlank
  private String name;

  @NotBlank
  private String surname;

  private String address;
}
