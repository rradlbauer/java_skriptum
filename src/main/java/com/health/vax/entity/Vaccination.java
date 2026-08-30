package com.health.vax.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * JPA entity mapped to the "vaccinations" table. Each vaccination belongs to
 * exactly one patient through a many-to-one relationship.
 */
@Entity
@Table(name = "vaccinations")
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Agent is required")
    private String agent;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Doctor is required")
    private String doctor;

    // Many-to-one: many vaccinations reference one patient. The foreign key
    // column "patient_id" in the vaccinations table links to the patients
    // table (see skriptum chapter 12.2).
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAgent() { return agent; }
    public void setAgent(String agent) { this.agent = agent; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getDoctor() { return doctor; }
    public void setDoctor(String doctor) { this.doctor = doctor; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
}
