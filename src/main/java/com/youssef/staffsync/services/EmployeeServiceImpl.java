package com.youssef.staffsync.services;

import com.youssef.staffsync.abstracts.EmployeeService;
import com.youssef.staffsync.dtos.EmployeeCreate;
import com.youssef.staffsync.dtos.EmployeeUpdate;
import com.youssef.staffsync.entities.Department;
import com.youssef.staffsync.entities.Employee;
import com.youssef.staffsync.repositories.DepartmentRepo;
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

    @Autowired
    private DepartmentRepo departmentRepo;

    public Employee findOne(UUID employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id " + employeeId + " not found"
                ));
        return employee;
    }

    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public void deleteOne(UUID employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id " + employeeId + " not found"
                ));

        employeeRepo.delete(employee);
    }

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

    public Employee create(EmployeeCreate employeeCreate) {
        Employee employee = new Employee();

        Department department = departmentRepo.findById(employeeCreate.departmentId())
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Department with id " + employeeCreate.departmentId() + " not found"
                ));

        employee.setFirstName(employeeCreate.firstName());
        employee.setLastName(employeeCreate.lastName());
        employee.setEmail(employeeCreate.email());
        employee.setPhoneNumber(employeeCreate.phoneNumber());
        employee.setPosition(employeeCreate.position());
        employee.setHireDate(employeeCreate.hireDate());
        employee.setDepartment(department);

        employeeRepo.save(employee);

        return employee;
    }

}
