package com.youssef.staffsync.abstracts;

import com.youssef.staffsync.dtos.EmployeeCreate;
import com.youssef.staffsync.dtos.EmployeeUpdate;
import com.youssef.staffsync.entities.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    Employee findOne(UUID employeeId);

    List<Employee> findAll();

    void deleteOne(UUID employeeId);

    Employee update(UUID employeeId, EmployeeUpdate updateEmployee);

    Employee create(EmployeeCreate employee);
}
