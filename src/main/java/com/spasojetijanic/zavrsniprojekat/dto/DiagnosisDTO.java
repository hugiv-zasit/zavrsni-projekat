package com.spasojetijanic.zavrsniprojekat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisDTO {

  private Long id;

  @NotBlank
  private String title;

  private String description;

  @NotNull
  private Long appointmentId;
}
