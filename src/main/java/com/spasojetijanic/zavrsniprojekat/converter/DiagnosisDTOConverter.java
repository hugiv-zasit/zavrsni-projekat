package com.spasojetijanic.zavrsniprojekat.converter;

import com.spasojetijanic.zavrsniprojekat.dto.DiagnosisDTO;
import com.spasojetijanic.zavrsniprojekat.model.Diagnosis;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiagnosisDTOConverter {
  @Autowired
  private ModelMapper modelMapper;

  public DiagnosisDTO convertToDto(Diagnosis diagnosis) {
    return modelMapper.map(diagnosis, DiagnosisDTO.class);
  }

  public Diagnosis convertToEntity(DiagnosisDTO diagnosisDTO) {
    return modelMapper.map(diagnosisDTO, Diagnosis.class);
  }
}
