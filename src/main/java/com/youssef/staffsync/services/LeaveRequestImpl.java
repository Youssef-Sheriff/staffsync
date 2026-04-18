package com.youssef.staffsync.services;

import com.youssef.staffsync.abstracts.EmployeeService;
import com.youssef.staffsync.abstracts.LeaveRequestService;
import com.youssef.staffsync.dtos.LeaveRequestCreate;
import com.youssef.staffsync.entities.Employee;
import com.youssef.staffsync.entities.LeaveRequest;
import com.youssef.staffsync.repositories.EmployeeRepo;
import com.youssef.staffsync.repositories.LeaveRequestRepo;
import com.youssef.staffsync.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LeaveRequestImpl implements LeaveRequestService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private LeaveRequestRepo leaveRequestRepo;

    public LeaveRequest Create(LeaveRequestCreate leaveRequestCreate, UUID employeeId) {

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id: " + employeeId + " not found"
                ));

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setStatus("PENDING");
        leaveRequest.setReason(leaveRequestCreate.reason());
        leaveRequest.setStartDate(leaveRequestCreate.startDate());
        leaveRequest.setEndDate(leaveRequestCreate.endDate());
        leaveRequest.setEmployee(employee);

        leaveRequestRepo.save(leaveRequest);

        return leaveRequest;
    }

    public List<LeaveRequest> findAllByEmployeeId(UUID employeeId) {
        return leaveRequestRepo.findAllByEmployeeId(employeeId);
    }
}
