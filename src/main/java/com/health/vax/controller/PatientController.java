package com.health.vax.controller;

import com.health.vax.entity.Patient;
import com.health.vax.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/patients";
    }

    @GetMapping("/patients")
    public String list(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patient-list";
    }

    @GetMapping("/patients/register")
    public String registerForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-register";
    }

    @PostMapping("/patients/register")
    public String register(@Valid @ModelAttribute Patient patient, BindingResult result) {
        if (result.hasErrors()) return "patient-register";
        patientRepository.save(patient);
        return "redirect:/patients";
    }
}
