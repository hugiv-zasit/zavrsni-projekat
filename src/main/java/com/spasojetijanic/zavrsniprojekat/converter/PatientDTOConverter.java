package com.spasojetijanic.zavrsniprojekat.converter;

import com.spasojetijanic.zavrsniprojekat.dto.PatientDTO;
import com.spasojetijanic.zavrsniprojekat.model.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientDTOConverter {
  @Autowired
  private ModelMapper modelMapper;

  public PatientDTO convertToDto(Patient patient) {
    return modelMapper.map(patient, PatientDTO.class);
  }

  public Patient convertToEntity(PatientDTO patientDTO) {
    return modelMapper.map(patientDTO, Patient.class);
  }
}
