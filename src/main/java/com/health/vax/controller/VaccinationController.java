package com.health.vax.controller;

import com.health.vax.entity.Patient;
import com.health.vax.entity.Vaccination;
import com.health.vax.repository.PatientRepository;
import com.health.vax.repository.VaccinationRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class VaccinationController {

    private final VaccinationRepository vaccinationRepository;
    private final PatientRepository patientRepository;

    public VaccinationController(VaccinationRepository vaccinationRepository,
                                 PatientRepository patientRepository) {
        this.vaccinationRepository = vaccinationRepository;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients/{patientId}/vaccinations")
    public String list(@PathVariable Long patientId, Model model) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        model.addAttribute("patient", patient);
        model.addAttribute("vaccinations", vaccinationRepository.findByPatientId(patientId));
        return "vaccination-list";
    }

    @GetMapping("/patients/{patientId}/vaccinations/add")
    public String addForm(@PathVariable Long patientId, Model model) {
        model.addAttribute("patient", patientRepository.findById(patientId).orElseThrow());
        model.addAttribute("vaccination", new Vaccination());
        return "vaccination-add";
    }

    @PostMapping("/patients/{patientId}/vaccinations/add")
    public String add(@PathVariable Long patientId,
                      @Valid @ModelAttribute Vaccination vaccination,
                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("patient", patientRepository.findById(patientId).orElseThrow());
            return "vaccination-add";
        }
        vaccination.setPatient(patientRepository.findById(patientId).orElseThrow());
        vaccinationRepository.save(vaccination);
        return "redirect:/patients/" + patientId + "/vaccinations";
    }
}
