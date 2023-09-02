package com.spasojetijanic.zavrsniprojekat.repository;

import com.spasojetijanic.zavrsniprojekat.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepo extends JpaRepository<Diagnosis, Long> {
}
