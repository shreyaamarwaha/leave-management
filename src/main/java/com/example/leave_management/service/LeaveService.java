package com.example.leave_management.service;

import com.example.leave_management.dto.LeaveApprovalDto;
import com.example.leave_management.dto.LeaveRequestDto;
import com.example.leave_management.dto.LeaveResponseDto;
import com.example.leave_management.entity.LeaveRequest;
import com.example.leave_management.entity.User;
import com.example.leave_management.repository.LeaveRequestRepository;
import com.example.leave_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaveService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final UserRepository userRepository;

    public LeaveResponseDto applyForLeave(LeaveRequestDto requestDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User employee = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployee(employee);
        leaveRequest.setStartDate(requestDto.getStartDate());
        leaveRequest.setEndDate(requestDto.getEndDate());
        leaveRequest.setReason(requestDto.getReason());
        leaveRequest.setLeaveType(requestDto.getLeaveType());

        LeaveRequest savedRequest = leaveRequestRepository.save(leaveRequest);
        return convertToDto(savedRequest);
    }

    public List<LeaveResponseDto> getMyLeaveRequests() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User employee = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return leaveRequestRepository.findByEmployee(employee)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<LeaveResponseDto> getAllPendingRequests() {
        return leaveRequestRepository.findByStatus(LeaveRequest.LeaveStatus.PENDING)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<LeaveResponseDto> getAllUpcomingLeaves() {
        java.time.LocalDate today = java.time.LocalDate.now();
        return leaveRequestRepository.findByStatusAndStartDateAfter(LeaveRequest.LeaveStatus.APPROVED, today)
                .stream()
                .map(this::convertToDto)
                .collect(java.util.stream.Collectors.toList());
    }

    public LeaveResponseDto approveOrRejectLeave(Long leaveId, LeaveApprovalDto approvalDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User admin = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        leaveRequest.setStatus(approvalDto.getStatus());
        leaveRequest.setApprovedBy(admin);
        leaveRequest.setAdminComments(approvalDto.getComments());

        LeaveRequest updatedRequest = leaveRequestRepository.save(leaveRequest);
        return convertToDto(updatedRequest);
    }

    private LeaveResponseDto convertToDto(LeaveRequest leaveRequest) {
        LeaveResponseDto dto = new LeaveResponseDto();
        dto.setId(leaveRequest.getId());
        dto.setEmployeeName(leaveRequest.getEmployee().getFullName());
        dto.setEmployeeUsername(leaveRequest.getEmployee().getUsername());
        dto.setStartDate(leaveRequest.getStartDate());
        dto.setEndDate(leaveRequest.getEndDate());
        dto.setReason(leaveRequest.getReason());
        dto.setLeaveType(leaveRequest.getLeaveType());
        dto.setStatus(leaveRequest.getStatus());
        dto.setCreatedAt(leaveRequest.getCreatedAt());
        dto.setUpdatedAt(leaveRequest.getUpdatedAt());
        
        if (leaveRequest.getApprovedBy() != null) {
            dto.setApprovedByName(leaveRequest.getApprovedBy().getFullName());
        }
        dto.setAdminComments(leaveRequest.getAdminComments());
        
        return dto;
    }
} 