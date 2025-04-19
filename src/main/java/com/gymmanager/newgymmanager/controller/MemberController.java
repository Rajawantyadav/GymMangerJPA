package com.gymmanager.newgymmanager.controller;

import com.gymmanager.newgymmanager.request.MemberReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.MemberResp;
import com.gymmanager.newgymmanager.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("addMember")
    public ResponseEntity<APiResp> addMember(@RequestBody MemberReq memberReq) {
        return memberService.addMember(memberReq);
    }
    @GetMapping("getMembers/{ownerId}")
    public ResponseEntity<MemberResp> getMembers(@PathVariable String ownerId) {
        return memberService.getMembers(ownerId);
    }
}
