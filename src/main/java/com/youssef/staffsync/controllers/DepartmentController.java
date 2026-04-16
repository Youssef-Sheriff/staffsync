package com.youssef.staffsync.controllers;


import com.youssef.staffsync.abstracts.DepartmentService;
import com.youssef.staffsync.dtos.DepartmentCreate;
import com.youssef.staffsync.entities.Department;
import com.youssef.staffsync.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Department>>> findAll() {
        List<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(departments), HttpStatus.OK);
    }

    @GetMapping("{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> findById(@PathVariable UUID departmentId) {
        Department existingDept = departmentService.findById(departmentId);
        return new ResponseEntity<>(new GlobalResponse<>(existingDept), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Department>> Create(@RequestBody @Valid DepartmentCreate departmentCreate) {
        Department newDepartment = departmentService.Create(departmentCreate);
        return new ResponseEntity<>(new GlobalResponse<>(newDepartment), HttpStatus.CREATED);
    }

    @DeleteMapping("{departmentId}")
    public ResponseEntity<Void> delete(@PathVariable UUID departmentId) {
        departmentService.delete(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
