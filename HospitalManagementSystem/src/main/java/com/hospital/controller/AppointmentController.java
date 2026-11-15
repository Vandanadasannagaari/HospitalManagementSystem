
package com.hospital.controller;

import com.hospital.entity.Appointment;
import com.hospital.entity.Patient;
import com.hospital.entity.Doctor;
import com.hospital.repository.AppointmentRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.repository.DoctorRepository;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentRepository appointments;
    private final PatientRepository patients;
    private final DoctorRepository doctors;

    public AppointmentController(
            AppointmentRepository appointments,
            PatientRepository patients,
            DoctorRepository doctors) {

        this.appointments = appointments;
        this.patients = patients;
        this.doctors = doctors;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Patient.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    setValue(patients.findById(Long.valueOf(text)).orElse(null));
                }
            }
        });

        binder.registerCustomEditor(Doctor.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    setValue(doctors.findById(Long.valueOf(text)).orElse(null));
                }
            }
        });
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("appointments", appointments.findAll());
        return "appointments";
    }

    @GetMapping("/new")
    public String createForm(Model model) {

        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patients.findAll());
        model.addAttribute("doctors", doctors.findAll());

        return "appointment-form";
    }

    @PostMapping("/save")
    public String save(
            @Valid @ModelAttribute Appointment appointment,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("patients", patients.findAll());
            model.addAttribute("doctors", doctors.findAll());
            return "appointment-form";
        }

        appointments.save(appointment);

        return "redirect:/appointments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        appointments.deleteById(id);
        return "redirect:/appointments";
    }
}
