package com.health.vax.repository;

import com.health.vax.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {
    List<Vaccination> findByPatientId(Long patientId);
}
