package com.hospital.controller;

import com.hospital.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final PatientRepository patients;
    private final DoctorRepository doctors;
    private final AppointmentRepository appointments;
    private final BillRepository bills;

    public HomeController(PatientRepository patients, DoctorRepository doctors,
                          AppointmentRepository appointments, BillRepository bills) {
        this.patients = patients; this.doctors = doctors;
        this.appointments = appointments; this.bills = bills;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("patientCount", patients.count());
        model.addAttribute("doctorCount", doctors.count());
        model.addAttribute("appointmentCount", appointments.count());
        model.addAttribute("billCount", bills.count());
        return "dashboard";
    }
}
