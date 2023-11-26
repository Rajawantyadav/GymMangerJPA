package com.gymmanager.newgymmanager.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberResp {
    private String error;
    private String msg;
    private List<GymMember> memebers;
    private String ownerId;
}
