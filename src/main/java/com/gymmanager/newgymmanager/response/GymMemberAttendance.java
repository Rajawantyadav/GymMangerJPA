package com.gymmanager.newgymmanager.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GymMemberAttendance {
    private String memberName;
    private String punchDate;
    private String punchInTime;
    private String punchOutTime;


}
