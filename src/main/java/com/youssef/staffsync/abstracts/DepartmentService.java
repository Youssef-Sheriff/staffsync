package com.youssef.staffsync.abstracts;

import com.youssef.staffsync.dtos.DepartmentCreate;
import com.youssef.staffsync.entities.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    Department findById(UUID departmentId);

    List<Department> findAll();

    Department Create(DepartmentCreate departmentCreate);

    void delete(UUID departmentId);
}
