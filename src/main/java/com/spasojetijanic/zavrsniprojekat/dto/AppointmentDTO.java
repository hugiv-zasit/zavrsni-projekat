package com.spasojetijanic.zavrsniprojekat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

  private Long id;
  private LocalDateTime appointmentStartingTime;

  private Long patientId;

  private Long doctorId;
}
