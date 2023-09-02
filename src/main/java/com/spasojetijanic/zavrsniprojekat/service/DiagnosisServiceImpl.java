package com.spasojetijanic.zavrsniprojekat.service;

import com.spasojetijanic.zavrsniprojekat.model.Diagnosis;
import com.spasojetijanic.zavrsniprojekat.repository.DiagnosisRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

  @Autowired
  private DiagnosisRepo diagnosisRepository;

  @Override
  public Optional<Diagnosis> findById(Long id) {
    return diagnosisRepository.findById(id);
  }

  @Override
  public List<Diagnosis> findAll() {
    return diagnosisRepository.findAll();
  }

  @Override
  public void save(Diagnosis diagnosis) {
    diagnosisRepository.save(diagnosis);
  }

  @Override
  public void deleteById(Long id) {
    diagnosisRepository.deleteById(id);
  }
}
