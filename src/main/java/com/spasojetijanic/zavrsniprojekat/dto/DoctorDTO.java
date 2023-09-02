package com.spasojetijanic.zavrsniprojekat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorDTO {

  private Long doctorId;

  @NotBlank
  private String name;

  @NotBlank
  private String surname;

  private String address;

  private Long userId;
}
