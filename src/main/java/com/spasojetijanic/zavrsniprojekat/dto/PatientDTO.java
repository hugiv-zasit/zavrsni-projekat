package com.spasojetijanic.zavrsniprojekat.dto;

import com.spasojetijanic.zavrsniprojekat.model.BloodGroup;
import com.spasojetijanic.zavrsniprojekat.model.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDTO {

  private Long patientId;

  @NotBlank
  private String name;

  @NotBlank
  private String surname;

  private String address;

  private BloodGroup bloodGroup;

  private Gender gender;

  private float height;

  private float weight;

  private Long userId;
}
