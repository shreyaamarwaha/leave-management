package com.example.leave_management.dto;

import com.example.leave_management.entity.LeaveRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveResponseDto {
    private Long id;
    private String employeeName;
    private String employeeUsername;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveRequest.LeaveType leaveType;
    private LeaveRequest.LeaveStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String approvedByName;
    private String adminComments;
} 