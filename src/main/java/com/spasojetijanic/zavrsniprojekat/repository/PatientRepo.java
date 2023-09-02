package com.spasojetijanic.zavrsniprojekat.repository;

import com.spasojetijanic.zavrsniprojekat.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient, Long> {

  Optional<Patient> findByName(String name);
}
