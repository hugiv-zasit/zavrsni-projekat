package com.spasojetijanic.zavrsniprojekat.controller;

import com.spasojetijanic.zavrsniprojekat.converter.DoctorDTOConverter;
import com.spasojetijanic.zavrsniprojekat.dto.DoctorDTO;
import com.spasojetijanic.zavrsniprojekat.model.Doctor;
import com.spasojetijanic.zavrsniprojekat.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/doctors")
public class DoctorController {

  @Autowired
  private DoctorService doctorService;
  @Autowired
  private DoctorDTOConverter doctorDTOConverter;

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('MANAGER') || #id == authentication.doctor.doctorId")
  public ResponseEntity<DoctorDTO> findById(@PathVariable Long id) {
    Optional<Doctor> doctor = doctorService.findById(id);
    return doctor.map(d -> ResponseEntity.ok(doctorDTOConverter.convertToDto(d)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  @PreAuthorize("hasRole('MANAGER')")
  public ResponseEntity<List<DoctorDTO>> findAll() {
    List<Doctor> doctors = doctorService.findAll();
    return ResponseEntity.ok(doctors.stream()
        .map(d -> doctorDTOConverter.convertToDto(d))
        .collect(Collectors.toList()));
  }

  @PostMapping
  @PreAuthorize("hasRole('MANAGER')")
  public ResponseEntity<Void> save(DoctorDTO doctorDTO) {
    doctorService.save(doctorDTOConverter.convertToEntity(doctorDTO));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping
  @PreAuthorize("hasRole('MANAGER')")
  public ResponseEntity<Void> update(DoctorDTO doctorDTO) {
    doctorService.update(doctorDTOConverter.convertToEntity(doctorDTO));
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('MANAGER')")
  public ResponseEntity<Void> deleteById(Long id) {
    doctorService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
