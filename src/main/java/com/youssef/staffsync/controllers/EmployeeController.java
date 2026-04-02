package com.youssef.staffsync.controllers;

import com.youssef.staffsync.entities.Employee;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>(List.of(
            new Employee(UUID.randomUUID(), "Ahmed", "Hassan", "ahmed.hassan@staffsync.com", "01012345678", LocalDate.of(2022, 3, 15), "Backend Developer", UUID.randomUUID()),
            new Employee(UUID.randomUUID(), "Mohamed", "Ali", "mohamed.ali@staffsync.com", "01123456789", LocalDate.of(2021, 7, 1), "Frontend Developer", UUID.randomUUID()),
            new Employee(UUID.randomUUID(), "Omar", "Khaled", "omar.khaled@staffsync.com", "01234567890", LocalDate.of(2023, 1, 10), "DevOps Engineer", UUID.randomUUID()),
            new Employee(UUID.randomUUID(), "Youssef", "Mahmoud", "youssef.mahmoud@staffsync.com", "01098765432", LocalDate.of(2020, 5, 20), "Team Lead", UUID.randomUUID()),
            new Employee(UUID.randomUUID(), "Karim", "Ibrahim", "karim.ibrahim@staffsync.com", "01587654321", LocalDate.of(2022, 11, 3), "QA Engineer", UUID.randomUUID())
    ));

    @GetMapping
    public ArrayList<Employee> findAll() {
        return employees;
    }

    @GetMapping("{employeeId}")
    public Optional<Employee> findOne(@PathVariable UUID employeeId) {

        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();
        return employee;
    }

    @PostMapping
    public Employee createOne(@RequestBody Employee employee) {

        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employees.add(employee);

        return employee;
    }

    @DeleteMapping("{employeeId}")
    public void deleteOne(@PathVariable UUID employeeId) {
        Optional<Employee> Employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();

        if (Employee.isPresent())
            employees.remove(Employee.get());

    }

    @PutMapping("{employeeId}")
    public Optional<Employee> update(@PathVariable UUID employeeId, @RequestBody Employee updateEmployee) {

        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst();
        if (employee.isPresent()) {
            employee.get().setFirstName(updateEmployee.getFirstName());
            employee.get().setLastName(updateEmployee.getLastName());
            employee.get().setEmail(updateEmployee.getEmail());
            employee.get().setPhoneNumber(updateEmployee.getPhoneNumber());
            employee.get().setPosition(updateEmployee.getPosition());
            employee.get().setHireDate(updateEmployee.getHireDate());
            employee.get().setDepartmentId(updateEmployee.getDepartmentId());
        }
        return employee;
    }
}
