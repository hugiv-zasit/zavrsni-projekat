package com.spasojetijanic.zavrsniprojekat.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiagnosisDTO {

  private Long diagnosisId;

  @NotBlank
  private String title;

  private String description;

  private Long appointmentId;

  private Long patientId;

  private Long doctorId;
}
