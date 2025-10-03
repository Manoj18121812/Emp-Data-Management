package com.example.empdatamanagement.controller;

import com.example.empdatamanagement.entity.Employee;
import com.example.empdatamanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/employees")
    public String redirectEmployees() {
        return "redirect:/";
    }

    @GetMapping("/")
    public String viewEmployees(@RequestParam(required = false) String keyword, Model model) {
        List<Employee> employees = (keyword == null || keyword.isEmpty())
                ? service.listAll()
                : service.search(keyword);

        model.addAttribute("employees", employees);
        model.addAttribute("keyword", keyword);
        return "employee-list";
    }


    @GetMapping("/employees/new")
    public String showForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }


    @PostMapping("/employees")
    public String save(@ModelAttribute("employee") @Valid Employee employee,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "employee-form";
        }
        service.save(employee);
        return "redirect:/";
    }


    @GetMapping("/employees/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("employee", service.get(id));
        return "employee-form";
    }


    @GetMapping("/employees/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }
}
