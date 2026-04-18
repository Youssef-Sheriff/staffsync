package com.youssef.staffsync.abstracts;

import com.youssef.staffsync.dtos.LeaveRequestCreate;
import com.youssef.staffsync.entities.LeaveRequest;

import java.util.List;
import java.util.UUID;

public interface LeaveRequestService {
    LeaveRequest Create(LeaveRequestCreate leaveRequestCreate, UUID employeeId);

    List<LeaveRequest> findAllByEmployeeId(UUID employeeId);
}
