package com.youssef.staffsync.abstracts;

import com.youssef.staffsync.entities.Employee;

import java.util.ArrayList;
import java.util.UUID;

public interface EmployeeService {
    Employee findOne(UUID employeeId);

    ArrayList<Employee> findAll();

    void deleteOne(UUID employeeId);

    Employee update(UUID employeeId, Employee updateEmployee);

    Employee create(Employee employee);
}
