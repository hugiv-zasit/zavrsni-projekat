package com.spasojetijanic.zavrsniprojekat.converter;

import com.spasojetijanic.zavrsniprojekat.dto.DoctorDTO;
import com.spasojetijanic.zavrsniprojekat.model.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorDTOConverter {
  @Autowired
  ModelMapper modelMapper;

  public DoctorDTO convertToDto(Doctor doctor) {
    return modelMapper.map(doctor, DoctorDTO.class);
  }

  public Doctor convertToEntity(DoctorDTO doctorDTO) {
    return modelMapper.map(doctorDTO, Doctor.class);
  }
}
