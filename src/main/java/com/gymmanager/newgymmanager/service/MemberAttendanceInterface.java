package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.response.MemberAttendanceResp;
import org.springframework.http.ResponseEntity;

public interface MemberAttendanceInterface {
    ResponseEntity<MemberAttendanceResp> getAttendanceList(String ownerId);
}
