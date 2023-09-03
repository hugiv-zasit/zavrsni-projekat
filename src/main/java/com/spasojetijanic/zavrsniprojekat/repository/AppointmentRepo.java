package com.spasojetijanic.zavrsniprojekat.repository;

import com.spasojetijanic.zavrsniprojekat.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
  boolean existsByDoctorIdAndAppointmentStartingTime(Long doctorId, Timestamp appointmentStartingTime);

  List<Appointment> findByPatientId(Long patientId);

  List<Appointment> findByDoctorId(Long doctorId);

  @Query(value = "SELECT * FROM Appointment a WHERE a.doctor_id = ?2 " +
      "AND CAST(appointment_starting_time AS LocalDate) = ?1", nativeQuery = true)
  List<Appointment> findByDateAndDoctorId(LocalDate appointmentDate, Long doctorId);
}
