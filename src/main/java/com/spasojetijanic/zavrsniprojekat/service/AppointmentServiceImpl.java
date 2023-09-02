package com.spasojetijanic.zavrsniprojekat.service;

import com.spasojetijanic.zavrsniprojekat.model.Appointment;
import com.spasojetijanic.zavrsniprojekat.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
  @Autowired
  private AppointmentRepo appointmentRepository;

  @Override
  public Optional<Appointment> findById(Long id) {
    return appointmentRepository.findById(id);
  }

  @Override
  public List<Appointment> findAll() {
    return appointmentRepository.findAll();
  }

  @Override
  public List<Appointment> findByPatientId(Long patientId) {
    return appointmentRepository.findByPatientId(patientId);
  }

  @Override
  public List<Appointment> findByDoctorId(Long doctorId) {
    return appointmentRepository.findByDoctorId(doctorId);
  }

  @Override
  public List<Appointment> findByDateAndByDoctorId(LocalDate appointmentDate, Long doctorId) {
    return appointmentRepository.findByDateAndDoctorId(appointmentDate, doctorId);
  }

  @Override
  public void save(Appointment appointment) {
    if (appointmentRepository.existsByDoctorIdAndAppointmentStartingTime(appointment.getDoctor().getId(),
        appointment.getAppointmentStartingTime())) {
      throw new RuntimeException("There is already a scheduled appointment at that time!");
    } else {
      appointmentRepository.save(appointment);
    }
  }

  @Override
  public void deleteById(Long id) {
    appointmentRepository.deleteById(id);
  }
}
