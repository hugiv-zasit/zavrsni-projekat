package com.spasojetijanic.zavrsniprojekat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AppointmentDTO {

  private Long appointmentId;
  private LocalDateTime appointmentStartingTime;
  private Long patientId;
  private Long doctorId;
  private Long diagnosisId;
}
