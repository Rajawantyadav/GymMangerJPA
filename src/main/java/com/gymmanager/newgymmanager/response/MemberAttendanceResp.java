package com.gymmanager.newgymmanager.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberAttendanceResp {
    private String error;
    private String msg;
    private List<GymMemberAttendance> memberAttendances;
    private String ownerId;
}
