package com.gymmanager.newgymmanager.controller;

import com.gymmanager.newgymmanager.request.MemberReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.MemberResp;
import com.gymmanager.newgymmanager.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("addMember")
    public ResponseEntity<APiResp> addMember(HttpServletRequest request, @RequestBody MemberReq memberReq) {
        String token =request.getHeader("Authorization");
        if(token==null || token.isEmpty()) {
            APiResp aPiResp=new APiResp();
            aPiResp.setError("true");
            return new ResponseEntity<>(aPiResp, HttpStatus.FORBIDDEN);
        }
        return memberService.addMember(memberReq);
    }
    @GetMapping("getMembers/{ownerId}")
    public ResponseEntity<MemberResp> getMembers(@PathVariable String ownerId) {
        return memberService.getMembers(ownerId);
    }
}
