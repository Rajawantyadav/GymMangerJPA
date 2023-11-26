package com.gymmanager.newgymmanager.controller;

import com.gymmanager.newgymmanager.response.MemberAttendanceResp;
import com.gymmanager.newgymmanager.service.MemberAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemberAttendanceController {
    @Autowired
    private MemberAttendanceService attendanceService;

    @GetMapping("getAttendance/{ownerId}")
    public ResponseEntity<MemberAttendanceResp> getAttendance(@PathVariable String ownerId) {
        return attendanceService.getAttendanceList(ownerId);
    }

}
