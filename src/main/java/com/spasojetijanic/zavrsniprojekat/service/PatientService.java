package com.spasojetijanic.zavrsniprojekat.service;

import com.spasojetijanic.zavrsniprojekat.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
  Optional<Patient> findById(Long id);

  Optional<Patient> findByName(String name);

  List<Patient> findAll();

  void save(Patient patient);

  void update(Patient patient);

  void deleteById(Long id);
}
