package com.spasojetijanic.zavrsniprojekat.converter;

import com.spasojetijanic.zavrsniprojekat.dto.AppointmentDTO;
import com.spasojetijanic.zavrsniprojekat.model.Appointment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDTOConverter {

  @Autowired
  private ModelMapper modelMapper;

  public AppointmentDTO convertToDto(Appointment appointment) {
    return modelMapper.map(appointment, AppointmentDTO.class);
  }

  public Appointment convertToEntity(AppointmentDTO appointmentDTO) {
    return modelMapper.map(appointmentDTO, Appointment.class);
  }
}
