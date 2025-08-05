package com.example.leave_management.repository;

import com.example.leave_management.entity.LeaveRequest;
import com.example.leave_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployee(User employee);
    List<LeaveRequest> findByStatus(LeaveRequest.LeaveStatus status);
    List<LeaveRequest> findByEmployeeAndStatus(User employee, LeaveRequest.LeaveStatus status);
    // Find all approved leaves with startDate in the future
    List<LeaveRequest> findByStatusAndStartDateAfter(LeaveRequest.LeaveStatus status, java.time.LocalDate date);
} 