package com.spasojetijanic.zavrsniprojekat.controller;

import com.spasojetijanic.zavrsniprojekat.converter.DoctorDTOConverter;
import com.spasojetijanic.zavrsniprojekat.dto.DoctorDTO;
import com.spasojetijanic.zavrsniprojekat.model.Doctor;
import com.spasojetijanic.zavrsniprojekat.model.User;
import com.spasojetijanic.zavrsniprojekat.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
  @PreAuthorize("hasRole('DOCTOR') && #id == authentication.principal.doctor.id")
  public ResponseEntity<DoctorDTO> findById(@PathVariable Long id) {
    Optional<Doctor> doctor = doctorService.findById(id);
    return doctor.map(d -> ResponseEntity.ok(doctorDTOConverter.convertToDto(d)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<DoctorDTO>> findAll() {
    List<Doctor> doctors = doctorService.findAll();
    return ResponseEntity.ok(doctors.stream()
        .map(d -> doctorDTOConverter.convertToDto(d))
        .collect(Collectors.toList()));
  }

  @PostMapping
  @PreAuthorize("hasRole('DOCTOR')")
  public ResponseEntity<Void> save(@Valid @RequestBody DoctorDTO doctorDTO) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Doctor doctor = doctorDTOConverter.convertToEntity(doctorDTO);
    doctor.setUser(user);
    doctorService.save(doctor);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping
  @PreAuthorize("hasRole('DOCTOR') && #doctorDTO.id == authentication.principal.doctor.id")
  public ResponseEntity<Void> update(@Valid @RequestBody DoctorDTO doctorDTO) {
    doctorService.update(doctorDTOConverter.convertToEntity(doctorDTO));
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('DOCTOR') && #id == authentication.principal.doctor.id")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    doctorService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
