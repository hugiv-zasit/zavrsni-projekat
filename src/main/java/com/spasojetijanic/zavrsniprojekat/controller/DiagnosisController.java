package com.spasojetijanic.zavrsniprojekat.controller;

import com.spasojetijanic.zavrsniprojekat.converter.DiagnosisDTOConverter;
import com.spasojetijanic.zavrsniprojekat.dto.DiagnosisDTO;
import com.spasojetijanic.zavrsniprojekat.model.Diagnosis;
import com.spasojetijanic.zavrsniprojekat.service.DiagnosisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/diagnoses")
@PreAuthorize("hasRole('DOCTOR')")
public class DiagnosisController {

  @Autowired
  private DiagnosisService diagnosisService;
  @Autowired
  private DiagnosisDTOConverter diagnosisDTOConverter;


  @GetMapping("/{id}")
  public ResponseEntity<DiagnosisDTO> findById(@PathVariable Long id) {
    Optional<Diagnosis> diagnosis = diagnosisService.findById(id);
    return diagnosis.map(d -> ResponseEntity.ok(diagnosisDTOConverter.convertToDto(d)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<DiagnosisDTO>> findAll() {
    List<Diagnosis> diagnoses = diagnosisService.findAll();
    return ResponseEntity.ok(diagnoses.stream()
        .map(diagnosis -> diagnosisDTOConverter.convertToDto(diagnosis))
        .collect(Collectors.toList()));

  }

  @PostMapping
  public ResponseEntity<Void> save(@Valid @RequestBody DiagnosisDTO diagnosisDTO) {
    diagnosisService.save(diagnosisDTOConverter.convertToEntity(diagnosisDTO));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    diagnosisService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
