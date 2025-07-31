package com.example.leave_management.dto;

import com.example.leave_management.entity.LeaveRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveApprovalDto {
    private LeaveRequest.LeaveStatus status;
    private String comments;
} 