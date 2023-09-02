package com.spasojetijanic.zavrsniprojekat.service;

import com.spasojetijanic.zavrsniprojekat.model.Patient;
import com.spasojetijanic.zavrsniprojekat.repository.PatientRepo;
import com.spasojetijanic.zavrsniprojekat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

  @Autowired
  PatientRepo patientRepository;
  @Autowired
  UserRepo userRepository;

  @Override
  public Optional<Patient> findById(Long id) {
    return patientRepository.findById(id);
  }

  @Override
  public Optional<Patient> findByName(String name) {
    return patientRepository.findByName(name);
  }

  @Override
  public List<Patient> findAll() {
    return patientRepository.findAll();
  }

  @Override
  public void save(Patient patient) {
    if (!userRepository.existsByEmail(patient.getUser().getEmail())) {
      patientRepository.save(patient);
    } else
      throw new RuntimeException("There already exists a user with that email!");
  }

  @Override
  public void update(Patient patient) {
    patientRepository.findById(patient.getId()).ifPresent(thisPatient -> {
      thisPatient.setName(patient.getName());
      thisPatient.setSurname(patient.getSurname());
      thisPatient.setAddress(patient.getAddress());
      thisPatient.setGender(patient.getGender());
      thisPatient.setHeight(patient.getHeight());
      thisPatient.setWeight(patient.getWeight());
      patientRepository.save(thisPatient);
    });
  }

  @Override
  public void deleteById(Long id) {
    patientRepository.deleteById(id);
  }
}
