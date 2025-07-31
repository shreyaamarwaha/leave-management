package com.example.leave_management.controller;

import com.example.leave_management.dto.LeaveRequestDto;
import com.example.leave_management.dto.LeaveResponseDto;
import com.example.leave_management.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
@PreAuthorize("hasRole('EMPLOYEE')")
public class EmployeeController {

    private final LeaveService leaveService;

    @PostMapping("/leave")
    public ResponseEntity<LeaveResponseDto> applyForLeave(@RequestBody LeaveRequestDto requestDto) {
        return ResponseEntity.ok(leaveService.applyForLeave(requestDto));
    }

    @GetMapping("/leave")
    public ResponseEntity<List<LeaveResponseDto>> getMyLeaveRequests() {
        return ResponseEntity.ok(leaveService.getMyLeaveRequests());
    }
} 