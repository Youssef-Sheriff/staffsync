package com.youssef.staffsync.repositories;


import com.youssef.staffsync.entities.LeaveRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LeaveRequestRepo extends CrudRepository<LeaveRequest, UUID> {
    List<LeaveRequest> findAllByEmployeeId(UUID employeeId);
}
