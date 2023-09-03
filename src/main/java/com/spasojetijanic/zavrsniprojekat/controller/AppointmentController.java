package com.spasojetijanic.zavrsniprojekat.controller;

import com.spasojetijanic.zavrsniprojekat.converter.AppointmentDTOConverter;
import com.spasojetijanic.zavrsniprojekat.dto.AppointmentDTO;
import com.spasojetijanic.zavrsniprojekat.model.Appointment;
import com.spasojetijanic.zavrsniprojekat.model.User;
import com.spasojetijanic.zavrsniprojekat.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

  @Autowired
  private AppointmentService appointmentService;
  @Autowired
  private AppointmentDTOConverter appointmentDTOConverter;

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('DOCTOR')")
  public ResponseEntity<AppointmentDTO> findById(@PathVariable Long id) {
    Optional<Appointment> appointment = appointmentService.findById(id);
    return appointment.map(a -> ResponseEntity.ok(appointmentDTOConverter.convertToDto(a)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }


  @GetMapping
  @PreAuthorize("hasRole('DOCTOR')")
  public ResponseEntity<List<AppointmentDTO>> findAll() {
    List<Appointment> appointments = appointmentService.findAll();
    return new ResponseEntity<>(appointments.stream()
        .map(appointment -> appointmentDTOConverter.convertToDto(appointment))
        .collect(Collectors.toList()), HttpStatus.OK);
  }

  @GetMapping("/patient")
  @PreAuthorize("hasRole('DOCTOR') || #patientId == authentication.principal.patient.id")
  public ResponseEntity<List<AppointmentDTO>> findByPatientId(@RequestParam Long patientId) {
    List<Appointment> appointments = appointmentService.findByPatientId(patientId);
    return new ResponseEntity<>(appointments.stream()
        .map(appointment -> appointmentDTOConverter.convertToDto(appointment))
        .collect(Collectors.toList()), HttpStatus.OK);
  }

  @GetMapping("/doctor")
  @PreAuthorize("hasRole('DOCTOR')")
  public ResponseEntity<List<AppointmentDTO>> findByDoctorId(@RequestParam Long doctorId) {
    List<Appointment> appointments = appointmentService.findByDoctorId(doctorId);
    return new ResponseEntity<>(appointments.stream()
        .map(appointment -> appointmentDTOConverter.convertToDto(appointment))
        .collect(Collectors.toList()), HttpStatus.OK);
  }

  @GetMapping("/doctor_date")
  @PreAuthorize("hasRole('DOCTOR')")
  public ResponseEntity<List<AppointmentDTO>> findByDateAndByDoctorId(
      @RequestParam LocalDate appointmentDate,
      @RequestParam Long doctorId
  ) {
    List<Appointment> appointments = appointmentService.findByDateAndByDoctorId(appointmentDate, doctorId);
    return new ResponseEntity<>(appointments.stream()
        .map(appointment -> appointmentDTOConverter.convertToDto(appointment))
        .collect(Collectors.toList()), HttpStatus.OK);
  }

  @PostMapping
  @PreAuthorize("hasRole('DOCTOR') || #appointmentDTO.patientId == authentication.principal.patient.id")
  public ResponseEntity<Void> save(@Valid @RequestBody AppointmentDTO appointmentDTO) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    appointmentService.save(appointmentDTOConverter.convertToEntity(appointmentDTO));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('DOCTOR')")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    appointmentService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
