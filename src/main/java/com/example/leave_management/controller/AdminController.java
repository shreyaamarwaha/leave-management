package com.example.leave_management.controller;

import com.example.leave_management.dto.LeaveApprovalDto;
import com.example.leave_management.dto.LeaveResponseDto;
import com.example.leave_management.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final LeaveService leaveService;

    @GetMapping("/leave/pending")
    public ResponseEntity<List<LeaveResponseDto>> getPendingLeaveRequests() {
        return ResponseEntity.ok(leaveService.getAllPendingRequests());
    }

    @PutMapping("/leave/{leaveId}/approve")
    public ResponseEntity<LeaveResponseDto> approveOrRejectLeave(
            @PathVariable Long leaveId,
            @RequestBody LeaveApprovalDto approvalDto) {
        return ResponseEntity.ok(leaveService.approveOrRejectLeave(leaveId, approvalDto));
    }
} 