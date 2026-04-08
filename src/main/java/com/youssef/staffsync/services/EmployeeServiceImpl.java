package com.youssef.staffsync.services;

import com.youssef.staffsync.abstracts.EmployeeService;
import com.youssef.staffsync.entities.Employee;
import com.youssef.staffsync.shared.CustomResponseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    ArrayList<Employee> employees = new ArrayList<>();

    @Override
    public Employee findOne(UUID employeeId) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id " + employeeId + " not found"
                ));
        return employee;
    }

    @Override
    public ArrayList<Employee> findAll() {
        return employees;
    }

    @Override
    public void deleteOne(UUID employeeId) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id " + employeeId + " not found"
                ));

        employees.remove(employee);
    }

    @Override
    public Employee update(UUID employeeId, Employee updateEmployee) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id " + employeeId + " not found"
                ));

        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());
        employee.setPhoneNumber(updateEmployee.getPhoneNumber());
        employee.setPosition(updateEmployee.getPosition());
        employee.setHireDate(updateEmployee.getHireDate());

        return employee;
    }

    @Override
    public Employee create(Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employees.add(employee);

        return employee;
    }

}
