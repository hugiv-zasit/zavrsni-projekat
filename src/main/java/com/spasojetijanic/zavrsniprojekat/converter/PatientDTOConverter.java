package com.spasojetijanic.zavrsniprojekat.converter;

import com.spasojetijanic.zavrsniprojekat.dto.DoctorDTO;
import com.spasojetijanic.zavrsniprojekat.dto.PatientDTO;
import com.spasojetijanic.zavrsniprojekat.model.Doctor;
import com.spasojetijanic.zavrsniprojekat.model.Patient;
import com.spasojetijanic.zavrsniprojekat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientDTOConverter {

  @Autowired
  UserRepo userRepository;

  public Doctor convertToEntity(DoctorDTO doctorDTO) {
    Doctor doctor;

    return Doctor.builder()
        .name(doctorDTO.getName())
        .surname(doctorDTO.getSurname())
        .address(doctorDTO.getAddress())
        .user(userRepository.findById(doctorDTO.getUserId()).orElseThrow(() ->
            new RuntimeException("User not found!")
        ))
        .build();
  }
  public PatientDTO convertToDto(Patient patient) {
    return PatientDTO.builder()
        .name(patient.getName())
        .surname(patient.getSurname())
        .address(patient.getAddress())
        .bloodGroup(patient.getBloodGroup())
        .gender(patient.getGender())
        .height(patient.getHeight())
        .weight(patient.getWeight())
        .userId(patient.getUser().getId())
        .build();
  }

  public Patient convertToEntity(PatientDTO patientDTO) {
    return Patient.builder()
        .name(patientDTO.getName())
        .surname(patientDTO.getSurname())
        .address(patientDTO.getAddress())
        .bloodGroup(patientDTO.getBloodGroup())
        .gender(patientDTO.getGender())
        .height(patientDTO.getHeight())
        .weight(patientDTO.getWeight())
//        .user(userRepository.findById(patientDTO.getUserId()).orElseThrow(() ->
//            new RuntimeException("User not found!")
//         ))
        .build();
  }
}
