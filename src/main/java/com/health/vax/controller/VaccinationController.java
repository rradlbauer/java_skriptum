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

/**
 * Spring MVC controller for vaccinations, nested under a patient:
 * /patients/{patientId}/vaccinations (see skriptum chapter 13.1).
 */
@Controller
public class VaccinationController {

    private final VaccinationRepository vaccinationRepository;
    private final PatientRepository patientRepository;

    // Both repositories are injected through the constructor.
    public VaccinationController(VaccinationRepository vaccinationRepository,
                                 PatientRepository patientRepository) {
        this.vaccinationRepository = vaccinationRepository;
        this.patientRepository = patientRepository;
    }

    // GET "/patients/{patientId}/vaccinations": the {patientId} part of the
    // URL is read with @PathVariable. We load the patient and all of his or
    // her vaccinations (derived query findByPatientId, see chapter 13.2).
    @GetMapping("/patients/{patientId}/vaccinations")
    public String list(@PathVariable Long patientId, Model model) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        model.addAttribute("patient", patient);
        model.addAttribute("vaccinations", vaccinationRepository.findByPatientId(patientId));
        return "vaccination-list";
    }

    // GET: shows the "add vaccination" form for a given patient.
    @GetMapping("/patients/{patientId}/vaccinations/add")
    public String addForm(@PathVariable Long patientId, Model model) {
        model.addAttribute("patient", patientRepository.findById(patientId).orElseThrow());
        model.addAttribute("vaccination", new Vaccination());
        return "vaccination-add";
    }

    // POST: receives the submitted vaccination form. The vaccination is linked
    // to the patient (many-to-one relation) before saving. On validation
    // errors the form is shown again, otherwise we redirect back to the list.
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
