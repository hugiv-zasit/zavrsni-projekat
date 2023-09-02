package com.spasojetijanic.zavrsniprojekat.service;

import com.spasojetijanic.zavrsniprojekat.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
  Optional<Doctor> findById(Long id);

  List<Doctor> findAll();

  void save(Doctor doctor);

  void update(Doctor doctor);

  void deleteById(Long id);
}
