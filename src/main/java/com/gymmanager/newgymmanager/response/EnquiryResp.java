package com.gymmanager.newgymmanager.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnquiryResp {
    private String error;
    private String msg;
    private List<GymEnquiryMember> enquiryMembers;
    private String ownerId;
}
