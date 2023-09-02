package com.spasojetijanic.zavrsniprojekat.repository;

import com.spasojetijanic.zavrsniprojekat.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
}
