package com.gymmanager.newgymmanager.controller;

import com.gymmanager.newgymmanager.model.EnquiryMember;
import com.gymmanager.newgymmanager.request.EnquiryMemberReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.EnquiryResp;
import com.gymmanager.newgymmanager.service.EnquiryMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EnquiryMemberController {
    @Autowired
    private EnquiryMemberService memberService;

    @PostMapping("addEnquiryMember")
    public ResponseEntity<APiResp> addEnquiryMember(@RequestBody EnquiryMemberReq enquiryMemberReq) {
        return memberService.addEnquiryMember(enquiryMemberReq);
    }
    @GetMapping("getEnquiryMembers/{ownerId}")
    public ResponseEntity<EnquiryResp> getEnquiryMembers(@PathVariable String ownerId) {
        return memberService.getEnquiryMembers(ownerId);
    }

    @PostMapping("updateEnquiryMember")
    public ResponseEntity<APiResp> updateExpense(@RequestBody EnquiryMember enquiryMemberDetails) {
        return memberService.updateEnquiryMember(enquiryMemberDetails);
    }

}
