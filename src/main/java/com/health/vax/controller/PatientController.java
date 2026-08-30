package com.health.vax.controller;

import com.health.vax.entity.Patient;
import com.health.vax.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Spring MVC controller for patients (see skriptum chapter 13.1).
 *
 * @Controller tells Spring to treat this class as an MVC controller that
 * handles HTTP requests. The class is discovered automatically through the
 * @ComponentScan started by @SpringBootApplication.
 */
@Controller
public class PatientController {

    // Spring Data repository used to access the "patients" table without
    // writing any SQL by hand (see skriptum chapter 13.2).
    private final PatientRepository patientRepository;

    // Constructor injection: Spring passes the repository instance here
    // automatically because PatientRepository is a Spring bean.
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // GET "/" simply redirects to "/patients" (Post/Redirect/Get friendly).
    @GetMapping("/")
    public String home() {
        return "redirect:/patients";
    }

    // GET "/patients": loads all patients from the database, puts them into
    // the Model under the name "patients" and renders the "patient-list" view.
    @GetMapping("/patients")
    public String list(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patient-list";
    }

    // GET "/patients/register": shows the registration form by adding an
    // empty Patient object to the model so the form can be bound to it.
    @GetMapping("/patients/register")
    public String registerForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-register";
    }

    // POST "/patients/register": receives the submitted form data.
    // @Valid triggers the Bean Validation rules defined on the Patient entity
    // (see skriptum chapter 13.3). If there are errors, the form is shown
    // again; otherwise the patient is saved and redirected to the list.
    @PostMapping("/patients/register")
    public String register(@Valid @ModelAttribute Patient patient, BindingResult result) {
        if (result.hasErrors()) return "patient-register";
        patientRepository.save(patient);
        return "redirect:/patients";
    }
}
