package com.health.vax.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Birthdate is required")
    private LocalDate birthdate;


    @Column(unique = true)
    private long ssn;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Vaccination> vaccinations = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public long getSsn() { return ssn; }
    public void setSsn(long ssn) { this.ssn = ssn; }

    public List<Vaccination> getVaccinations() { return vaccinations; }
    public void setVaccinations(List<Vaccination> vaccinations) { this.vaccinations = vaccinations; }
}
