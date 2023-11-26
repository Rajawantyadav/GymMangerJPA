package com.gymmanager.newgymmanager.service;

import com.gymmanager.newgymmanager.model.EnquiryMember;
import com.gymmanager.newgymmanager.request.EnquiryMemberReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.EnquiryResp;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EnquiryMemberInterface {
    ResponseEntity<APiResp> addEnquiryMember(EnquiryMemberReq enquiryMemberDetails);
    ResponseEntity<APiResp> updateEnquiryMember(EnquiryMember enquiryMemberDetails);
    ResponseEntity<EnquiryResp> getEnquiryMembers(String ownerId);
}
