package com.spasojetijanic.zavrsniprojekat.service;

import com.spasojetijanic.zavrsniprojekat.model.Diagnosis;

import java.util.List;
import java.util.Optional;

public interface DiagnosisService {

  Optional<Diagnosis> findById(Long id);

  List<Diagnosis> findAll();

  void save(Diagnosis diagnosis);

  void deleteById(Long id);
}
