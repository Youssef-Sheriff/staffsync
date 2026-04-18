package com.youssef.staffsync.controllers;

import com.youssef.staffsync.abstracts.EmployeeService;
import com.youssef.staffsync.abstracts.LeaveRequestService;
import com.youssef.staffsync.dtos.EmployeeCreate;
import com.youssef.staffsync.dtos.EmployeeUpdate;
import com.youssef.staffsync.dtos.LeaveRequestCreate;
import com.youssef.staffsync.entities.Employee;
import com.youssef.staffsync.entities.LeaveRequest;
import com.youssef.staffsync.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveRequestService leaveRequestService;

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Employee>>> findAll() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> findOne(@PathVariable UUID employeeId) {
        Employee employee = employeeService.findOne(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeeId) {
        employeeService.deleteOne(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> update(
            @PathVariable UUID employeeId,
            @RequestBody @Valid EmployeeUpdate updateEmployee) {
        Employee employee = employeeService.update(employeeId, updateEmployee);
        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid EmployeeCreate employeeCreate) {
        Employee newEmployee = employeeService.create(employeeCreate);
        return new ResponseEntity<>(new GlobalResponse<>(newEmployee), HttpStatus.CREATED);
    }

    @PostMapping("/{employeeId}/leave-request")
    public ResponseEntity<GlobalResponse<LeaveRequest>> createLeaveRequest(
            @RequestBody @Valid LeaveRequestCreate leaveRequestCreate, @PathVariable UUID employeeId) {
        LeaveRequest newLeaveRequest = leaveRequestService.Create(leaveRequestCreate, employeeId);

        return new ResponseEntity<>(new GlobalResponse<>(newLeaveRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/leave-request")
    public ResponseEntity<GlobalResponse<List<LeaveRequest>>> getLeaveRequest(@PathVariable UUID employeeId) {
        List<LeaveRequest> leaveRequests = leaveRequestService.findAllByEmployeeId(employeeId);

        return new ResponseEntity<>(new GlobalResponse<>(leaveRequests), HttpStatus.OK);
    }

}
