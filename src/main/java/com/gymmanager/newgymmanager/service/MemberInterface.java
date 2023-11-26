package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.model.Member;
import com.gymmanager.newgymmanager.request.LoginReq;
import com.gymmanager.newgymmanager.request.MemberReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.MemberResp;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberInterface {
    ResponseEntity<APiResp> addMember(MemberReq member);

    ResponseEntity<MemberResp> getMembers(String ownerId);
}
