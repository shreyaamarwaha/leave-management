package com.example.leave_management.dto;

import com.example.leave_management.entity.LeaveRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveRequest.LeaveType leaveType;
} 