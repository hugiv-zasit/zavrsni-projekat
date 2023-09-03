package com.spasojetijanic.zavrsniprojekat.converter;

import com.spasojetijanic.zavrsniprojekat.dto.DoctorDTO;
import com.spasojetijanic.zavrsniprojekat.model.Doctor;
import com.spasojetijanic.zavrsniprojekat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorDTOConverter {

  @Autowired
  UserRepo userRepository;

  public DoctorDTO convertToDto(Doctor doctor) {
    return DoctorDTO.builder()
        .name(doctor.getName())
        .surname(doctor.getSurname())
        .address(doctor.getAddress())
        .userId(doctor.getUser().getId())
        .build();
  }

  public Doctor convertToEntity(DoctorDTO doctorDTO) {
    Doctor doctor;

    return Doctor.builder()
        .name(doctorDTO.getName())
        .surname(doctorDTO.getSurname())
        .address(doctorDTO.getAddress())
//        .user(userRepository.findById(doctorDTO.getUserId()).orElseThrow(() ->
//            new RuntimeException("User not found!")
//        ))
        .build();
  }
}
