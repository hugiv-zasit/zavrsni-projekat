package com.spasojetijanic.zavrsniprojekat.service;

import com.spasojetijanic.zavrsniprojekat.model.Doctor;
import com.spasojetijanic.zavrsniprojekat.repository.DoctorRepo;
import com.spasojetijanic.zavrsniprojekat.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

  @Autowired
  UserRepo userRepository;
  @Autowired
  private DoctorRepo doctorRepository;

  @Override
  public Optional<Doctor> findById(Long id) {
    return doctorRepository.findById(id);
  }

  @Override
  public List<Doctor> findAll() {
    return doctorRepository.findAll();
  }

  @Override
  public void save(Doctor doctor) {
    if (!userRepository.existsByEmail(doctor.getUser().getEmail())) {
      doctorRepository.save(doctor);
    } else
      throw new RuntimeException("There already exists a user with that email!");
  }

  @Override
  public void update(Doctor doctor) {
    doctorRepository.findById(doctor.getId()).ifPresent(thisDoctor -> {
      thisDoctor.setName(doctor.getName());
      thisDoctor.setSurname(doctor.getSurname());
      thisDoctor.setAddress(doctor.getAddress());
      doctorRepository.save(thisDoctor);
    });
  }

  @Override
  public void deleteById(Long id) {
    doctorRepository.deleteById(id);
  }
}
