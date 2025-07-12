package com.example.employeedirectory.controller;

import com.example.employeedirectory.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>(List.of(
        new Employee(1, "John", "Doe", "john.doe@example.com", "HR", "Manager"),
        new Employee(2, "Jane", "Smith", "jane.smith@example.com", "IT", "Developer"),
        new Employee(3, "David", "Brown", "david.brown@example.com", "Finance", "Analyst")
    ));
    private int nextId = 4;
    @GetMapping("/")
public String dashboard(
        @RequestParam(value = "query", required = false) String query,
        @RequestParam(value = "department", required = false) String department,
        @RequestParam(value = "role", required = false) String role,
        Model model) {

    List<Employee> filtered = employees;

    if (query != null && !query.trim().isEmpty()) {
        String lowerQuery = query.toLowerCase();
        filtered = filtered.stream()
                .filter(emp -> emp.getFirstName().toLowerCase().contains(lowerQuery)
                        || emp.getLastName().toLowerCase().contains(lowerQuery)
                        || emp.getEmail().toLowerCase().contains(lowerQuery))
                .toList();
        model.addAttribute("query", query);
    }

    if (department != null && !department.isEmpty()) {
        filtered = filtered.stream()
                .filter(emp -> emp.getDepartment().equalsIgnoreCase(department))
                .toList();
        model.addAttribute("selectedDepartment", department);
    }

    if (role != null && !role.isEmpty()) {
        filtered = filtered.stream()
                .filter(emp -> emp.getRole().equalsIgnoreCase(role))
                .toList();
        model.addAttribute("selectedRole", role);
    }

    // Unique departments and roles for dropdowns
    Set<String> departments = employees.stream()
            .map(Employee::getDepartment)
            .collect(Collectors.toSet());

    Set<String> roles = employees.stream()
            .map(Employee::getRole)
            .collect(Collectors.toSet());

    model.addAttribute("departments", departments);
    model.addAttribute("roles", roles);
    model.addAttribute("employees", filtered);

    return "dashboard";
}



    @GetMapping("/form")
public String showForm(@RequestParam(required = false) Integer id, Model model) {
    if (id != null) {
        // Edit mode
        employees.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .ifPresent(emp -> model.addAttribute("employee", emp));
    } else {
        // Add mode
        model.addAttribute("employee", new Employee());
    }
    return "form";
}


    @PostMapping("/submit")
    public String handleSubmit(@RequestParam Map<String, String> formData) {
        int id = formData.get("id") == null || formData.get("id").isEmpty() ? 0 : Integer.parseInt(formData.get("id"));
        String firstName = formData.get("firstName");
        String lastName = formData.get("lastName");
        String email = formData.get("email");
        String department = formData.get("department");
        String role = formData.get("role");

        if (id == 0) {
            employees.add(new Employee(nextId++, firstName, lastName, email, department, role));
        } else {
            employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .ifPresent(emp -> {
                    emp.setFirstName(firstName);
                    emp.setLastName(lastName);
                    emp.setEmail(email);
                    emp.setDepartment(department);
                    emp.setRole(role);
                });
        }
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam int id) {
        employees.removeIf(e -> e.getId() == id);
        return "redirect:/";
    }
}
