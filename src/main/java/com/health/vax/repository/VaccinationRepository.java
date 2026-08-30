package com.health.vax.repository;

import com.health.vax.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Spring Data JPA repository for the Vaccination entity (see skriptum ch. 13.2).
 */
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    // Derived query method: Spring Data builds the SQL from the method name.
    // "findByPatientId" translates to:
    //   SELECT * FROM vaccinations WHERE patient_id = ?
    // This returns all vaccinations that belong to the given patient id.
    List<Vaccination> findByPatientId(Long patientId);
}
