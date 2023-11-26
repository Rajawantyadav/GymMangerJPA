package com.gymmanager.newgymmanager.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberReq {
    private String memberName;
    private String memberEmail;
    private String memberMobile;
    private String memberDob;
    private String memberGender;
    private String memberWeight;
    private String memberBatch;
    private String memberPlan;
    private long ownerId;
}
