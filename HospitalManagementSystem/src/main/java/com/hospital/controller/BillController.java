
package com.hospital.controller;

import com.hospital.entity.Bill;
import com.hospital.entity.Patient;
import com.hospital.repository.BillRepository;
import com.hospital.repository.PatientRepository;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/bills")
public class BillController {

    private final BillRepository bills;
    private final PatientRepository patients;

    public BillController(BillRepository bills, PatientRepository patients) {
        this.bills = bills;
        this.patients = patients;
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
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("bills", bills.findAll());
        return "bills";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("bill", new Bill());
        model.addAttribute("patients", patients.findAll());
        return "bill-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Bill bill,
                       BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("patients", patients.findAll());
            return "bill-form";
        }

        bills.save(bill);
        return "redirect:/bills";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bills.deleteById(id);
        return "redirect:/bills";
    }
}
