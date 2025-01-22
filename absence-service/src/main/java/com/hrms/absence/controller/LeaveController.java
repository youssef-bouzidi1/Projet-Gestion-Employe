package com.hrms.absence.controller;

import com.hrms.absence.model.Leave;
import com.hrms.absence.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    // Endpoint to request leave
    @PostMapping
    public ResponseEntity<Leave> requestLeave(@RequestBody Leave leave) {
        Leave createdLeave = leaveService.requestLeave(leave);
        return ResponseEntity.ok(createdLeave);
    }

    // Endpoint to approve a leave request
    @PutMapping("/{leaveId}/approve/{approverId}")
    public ResponseEntity<Leave> approveLeave(@PathVariable Long leaveId, @PathVariable Long approverId) {
        Leave updatedLeave = leaveService.approveLeave(leaveId, approverId);
        return new ResponseEntity<>(updatedLeave, HttpStatus.OK);
    }

    // Endpoint to reject a leave request
    @PutMapping("/{leaveId}/reject/{approverId}")
    public ResponseEntity<Leave> rejectLeave(@PathVariable Long leaveId, @PathVariable Long approverId) {
        Leave rejectedLeave = leaveService.rejectLeave(leaveId, approverId);
        return new ResponseEntity<>(rejectedLeave, HttpStatus.OK);
    }

    // Endpoint to get leave details by leaveId
    @GetMapping("/{leaveId}")
    public ResponseEntity<Leave> getLeaveById(@PathVariable Long leaveId) {
        Leave leave = leaveService.getLeaveById(leaveId);
        return ResponseEntity.ok(leave);
    }

    // Endpoint to get all leaves (optionally filtered by employeeId)
    @GetMapping
    public ResponseEntity<List<Leave>> getAllLeaves(@RequestParam(required = false) Long employeeId) {
        List<Leave> leaves = (employeeId != null)
                ? leaveService.getLeavesByEmployeeId(employeeId)
                : leaveService.getAllLeaves();
        return ResponseEntity.ok(leaves);
    }
}
