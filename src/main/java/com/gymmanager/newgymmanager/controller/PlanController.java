package com.gymmanager.newgymmanager.controller;

import com.gymmanager.newgymmanager.model.Plan;
import com.gymmanager.newgymmanager.request.PlanReq;
import com.gymmanager.newgymmanager.response.APiResp;
import com.gymmanager.newgymmanager.response.PlanResp;
import com.gymmanager.newgymmanager.service.PlanService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PlanController {
    @Autowired
    private PlanService planService;

    @PostMapping("addPlan")
    public ResponseEntity<APiResp> addPlan(HttpServletRequest request, @RequestBody PlanReq planReq) {
        String token =request.getHeader("Authorization");
        if(token==null || token.isEmpty()) {
            APiResp aPiResp=new APiResp();
            aPiResp.setError("true");
            return new ResponseEntity<>(aPiResp, HttpStatus.FORBIDDEN);
        }
        return planService.addPlan(planReq);
    }
    @GetMapping("getPlan/{ownerId}")
    public ResponseEntity<PlanResp> getPlan(@PathVariable String ownerId) {
        return planService.getPlan(ownerId);
    }
    @PostMapping("updatePlan")
    public ResponseEntity<APiResp> updatePlan(HttpServletRequest request,@RequestBody Plan planDetails) {
        String token =request.getHeader("Authorization");
        if(token==null || token.isEmpty()) {
            APiResp aPiResp=new APiResp();
            aPiResp.setError("true");
            return new ResponseEntity<>(aPiResp, HttpStatus.FORBIDDEN);
        }
        return planService.updatePlan(planDetails);
    }
}
