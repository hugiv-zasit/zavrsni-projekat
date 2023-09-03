package com.spasojetijanic.zavrsniprojekat.dto;

import com.spasojetijanic.zavrsniprojekat.model.BloodGroup;
import com.spasojetijanic.zavrsniprojekat.model.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

  private Long id;

  @NotBlank
  private String name;

  @NotBlank
  private String surname;

  private String address;

  private BloodGroup bloodGroup;

  private Gender gender;

  private float height;

  private float weight;

  @NotBlank
  private Long userId;
}
