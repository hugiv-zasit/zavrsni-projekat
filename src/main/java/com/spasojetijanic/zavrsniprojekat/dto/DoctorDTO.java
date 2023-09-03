package com.spasojetijanic.zavrsniprojekat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

  private Long id;

  @NotBlank(message = "Doctor must have name!")
  private String name;

  @NotBlank(message = "Doctor must have surname!")
  private String surname;

  private String address;

  private Long userId;
}
