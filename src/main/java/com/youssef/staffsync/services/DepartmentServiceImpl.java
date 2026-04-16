package com.youssef.staffsync.services;


import com.youssef.staffsync.abstracts.DepartmentService;
import com.youssef.staffsync.dtos.DepartmentCreate;
import com.youssef.staffsync.entities.Department;
import com.youssef.staffsync.repositories.DepartmentRepo;
import com.youssef.staffsync.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    public Department findById(UUID departmentId) {
        Department dpt = departmentRepo.findById(departmentId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Department with id: " + departmentId + " not found."
                ));
        return dpt;
    }

    public Department Create(DepartmentCreate departmentCreate) {
        Department newDepartment = new Department();
        newDepartment.setName(departmentCreate.name());

        departmentRepo.save(newDepartment);
        return newDepartment;
    }

    public void delete(UUID departmentId) {
        Department dpt = departmentRepo.findById(departmentId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Department with id: " + departmentId + " not found."
                ));
        departmentRepo.delete(dpt);
    }
}
