package com.spasojetijanic.zavrsniprojekat.controller;

import com.spasojetijanic.zavrsniprojekat.converter.PatientDTOConverter;
import com.spasojetijanic.zavrsniprojekat.dto.PatientDTO;
import com.spasojetijanic.zavrsniprojekat.model.Patient;
import com.spasojetijanic.zavrsniprojekat.model.User;
import com.spasojetijanic.zavrsniprojekat.service.PatientService;
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
@RequestMapping(value = "/api/v1/patients")
public class PatientController {

  @Autowired
  private PatientService patientService;
  @Autowired
  private PatientDTOConverter patientDTOConverter;

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('DOCTOR') || #id == authentication.principal.patient.id")
  public ResponseEntity<PatientDTO> findById(@PathVariable Long id) {
    Optional<Patient> patient = patientService.findById(id);
    return patient.map(p -> ResponseEntity.ok(patientDTOConverter.convertToDto(p)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/name")
  @PreAuthorize("hasRole('DOCTOR')")
  public ResponseEntity<PatientDTO> findByName(@RequestParam String name) {
    Optional<Patient> patient = patientService.findByName(name);
    return patient.map(p -> ResponseEntity.ok(patientDTOConverter.convertToDto(p)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  @PreAuthorize("hasRole('DOCTOR')")
  public ResponseEntity<List<PatientDTO>> findAll() {
    List<Patient> patients = patientService.findAll();
    return ResponseEntity.ok(patients.stream()
        .map(p -> patientDTOConverter.convertToDto(p))
        .collect(Collectors.toList()));
  }

  @PostMapping
  @PreAuthorize("hasRole('PATIENT')")
  public ResponseEntity<Void> save(@Valid @RequestBody PatientDTO patientDTO) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Patient patient = patientDTOConverter.convertToEntity(patientDTO);
    patient.setUser(user);
    patientService.save(patient);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping
  @PreAuthorize("hasRole('PATIENT') && #patientDTO.patientId == authentication.principal.patient.id")
  public ResponseEntity<Void> update(@Valid @RequestBody PatientDTO patientDTO) {
    patientService.update(patientDTOConverter.convertToEntity(patientDTO));
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('PATIENT') && #id == authentication.principal.patient.id")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    patientService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
