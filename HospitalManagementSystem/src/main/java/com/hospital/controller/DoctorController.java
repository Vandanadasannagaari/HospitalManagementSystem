package com.hospital.controller;

import com.hospital.entity.Doctor;
import com.hospital.repository.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorRepository repository;
    public DoctorController(DoctorRepository repository) { this.repository = repository; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("doctors", repository.findAll());
        return "doctors";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctor-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Doctor doctor, BindingResult result) {
        if (result.hasErrors()) return "doctor-form";
        repository.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", repository.findById(id).orElseThrow());
        return "doctor-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/doctors";
    }
}
