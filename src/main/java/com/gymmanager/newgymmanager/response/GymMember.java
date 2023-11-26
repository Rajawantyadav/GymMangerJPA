package com.gymmanager.newgymmanager.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GymMember {
    private String memberName;
    private String joiningDate;
    private String planExpireDate;
    private String memberEmail;
    private String memberMobile;
    private String memberDob;
    private String memberGender;
    private String activePlanName;
}
