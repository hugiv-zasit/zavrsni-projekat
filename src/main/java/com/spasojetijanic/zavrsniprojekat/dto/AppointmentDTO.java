package com.spasojetijanic.zavrsniprojekat.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

  private Long id;

  @NotNull
  private String appointmentStartingTime;

  @NotNull
  private Long patientId;

  @NotNull
  private Long doctorId;
}
