package com.health.vax.repository;

import com.health.vax.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Patient entity (see skriptum chapter 13.2).
 *
 * By extending JpaRepository<Patient, Long> we get ready-made CRUD methods
 * such as findAll(), findById(), save() and deleteById() for free. Spring
 * generates the implementation at runtime, so no method body is required.
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
