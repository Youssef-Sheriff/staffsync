package com.youssef.staffsync.services;

import com.youssef.staffsync.abstracts.EmployeeService;
import com.youssef.staffsync.dtos.EmployeeCreate;
import com.youssef.staffsync.dtos.EmployeeUpdate;
import com.youssef.staffsync.entities.Employee;
import com.youssef.staffsync.repositories.EmployeeRepo;
import com.youssef.staffsync.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee findOne(UUID employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id " + employeeId + " not found"
                ));
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public void deleteOne(UUID employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id " + employeeId + " not found"
                ));

        employeeRepo.delete(employee);
    }

    @Override
    public Employee update(UUID employeeId, EmployeeUpdate updateEmployee) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id " + employeeId + " not found"
                ));

        employee.setFirstName(updateEmployee.firstName());
        employee.setLastName(updateEmployee.lastName());
        employee.setPhoneNumber(updateEmployee.phoneNumber());
        employee.setPosition(updateEmployee.position());

        employeeRepo.save(employee);
        return employee;
    }

    @Override
    public Employee create(EmployeeCreate employeeCreate) {
        Employee employee = new Employee();
        employee.setDepartmentId(UUID.randomUUID());
        employee.setFirstName(employeeCreate.firstName());
        employee.setLastName(employeeCreate.lastName());
        employee.setEmail(employeeCreate.email());
        employee.setPhoneNumber(employeeCreate.phoneNumber());
        employee.setPosition(employeeCreate.position());
        employee.setHireDate(employeeCreate.hireDate());

        employeeRepo.save(employee);

        return employee;
    }

}
