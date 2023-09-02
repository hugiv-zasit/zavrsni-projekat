package com.spasojetijanic.zavrsniprojekat.service;

import com.spasojetijanic.zavrsniprojekat.model.Appointment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
  Optional<Appointment> findById(Long id);

  List<Appointment> findAll();

  List<Appointment> findByPatientId(Long patientId);

  List<Appointment> findByDoctorId(Long doctorId);

  List<Appointment> findByDateAndByDoctorId(LocalDate appointmentDate, Long doctorId);

  void save(Appointment appointment);

  void deleteById(Long id);
}
